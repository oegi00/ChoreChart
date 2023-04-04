package com.example.chorechart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chorechart.R;
import com.example.chorechart.data.Chore;
import com.example.chorechart.data.Roommate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Locale;

public class ChoreDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView title;
    private EditText location;
    private EditText assignee;
    private EditText deadline;
    private EditText description;
    private Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chore_details);

        toolbar = findViewById(R.id.NewchoreDetailsToolbar);

        title = findViewById(R.id.chore_details_title);
        location = findViewById(R.id.chore_details_location_entry);
        assignee = findViewById(R.id.chore_details_assignee_entry);
        deadline = findViewById(R.id.chore_details_deadline_entry);
        description = findViewById(R.id.chore_details_description_entry);
        save = findViewById(R.id.chore_details_save);

        Intent choreDetailsActivityIntent = getIntent();
        Chore chore = (Chore) choreDetailsActivityIntent.getSerializableExtra("chore");
        ArrayList<Roommate> roommates = (ArrayList<Roommate>) choreDetailsActivityIntent.getSerializableExtra("roommates");
        ArrayList<Chore> choreList = (ArrayList<Chore>) choreDetailsActivityIntent.getSerializableExtra("chore_list");
        int chorePosition = Integer.parseInt(choreDetailsActivityIntent.getStringExtra("position"));

        title.setText(chore.getChoreName());
        location.setText(chore.getLocation());
        assignee.setText(chore.getAssignee());
        deadline.setText(chore.getDeadline());
        description.setText(chore.getDescription());

        // Adds a back button to the toolbar
        // When clicked, goes back to the previous activity
        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_action_back, getTheme()));
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();    // Using built-in function
        });

        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setSelectedItemId(R.id.navigation_search);

        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    return true;
                case R.id.fragment_menu:
                    Intent homepageIntent = new Intent(ChoreDetailsActivity.this, HomepageActivity.class);
                    homepageIntent.putExtra("chore_list", choreList);
                    homepageIntent.putExtra("roommates", roommates);
                    startActivity(homepageIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_roommates:
                    Intent newRoommatesIntent = new Intent(ChoreDetailsActivity.this, AddingRoommateProfileActivity.class);
                    newRoommatesIntent.putExtra("chore_list", choreList);
                    newRoommatesIntent.putExtra("roommates", roommates);
                    startActivity(newRoommatesIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_add:
                    Intent addNewChoreIntent = new Intent(ChoreDetailsActivity.this, AddANewChore.class);
                    addNewChoreIntent.putExtra("chore_list", choreList);
                    addNewChoreIntent.putExtra("roommates", roommates);
                    startActivity(addNewChoreIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_profile:
                    Intent profileActivity = new Intent(ChoreDetailsActivity.this, ProfilePage.class);
                    profileActivity.putExtra("chore_list", choreList);
                    profileActivity.putExtra("roommates", roommates);
                    profileActivity.putExtra("roommate", roommates.get(0));
                    startActivity(profileActivity);
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = (datePicker, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(calendar);
        };

        deadline.setOnClickListener(view -> new DatePickerDialog(ChoreDetailsActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show());

        save.setOnClickListener(view -> {
            Toast.makeText(this, "Chore Saved", Toast.LENGTH_SHORT).show();
            Chore modifiedChore = new Chore(title.getText().toString(), assignee.getText().toString(), location.getText().toString(), deadline.getText().toString(), description.getText().toString());
            choreList.set(chorePosition, modifiedChore);
            Intent homePageActivity = new Intent(this, HomepageActivity.class);
            homePageActivity.putExtra("roommates", roommates);
            homePageActivity.putExtra("chore_list", choreList);
            startActivity(homePageActivity);
        });

        // Utilizes InputValidator class
        deadline.addTextChangedListener(new InputValidator());
        location.addTextChangedListener(new InputValidator());
        assignee.addTextChangedListener(new InputValidator());
        description.addTextChangedListener(new InputValidator());
    }

    // Removes virtual keyboard from the screen when the user taps anywhere on the screen except EditText boxes
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    private class InputValidator implements TextWatcher {

        private String beforeLocation;
        private String beforeAssignee;
//        private String deadline;
        private String beforeDescription;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            beforeLocation = location.getText().toString();
            beforeAssignee = assignee.getText().toString();
            beforeDescription = description.getText().toString();
//            Log.d("beforeTextChanged: ", beforeLocation);
//            Log.d("beforeTextChanged: ", beforeAssignee);
//            Log.d("beforeTextChanged: ", beforeDescription);
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (beforeLocation != location.getText().toString() || beforeAssignee != assignee.getText().toString() || beforeDescription != description.getText().toString()) {
                save.setText("Save");
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            beforeLocation = null;
            beforeAssignee = null;
            beforeDescription = null;
        }
    }

    private void updateLabel(Calendar calendar) {
        String format = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        deadline.setText(dateFormat.format(calendar.getTime()));
    }
}