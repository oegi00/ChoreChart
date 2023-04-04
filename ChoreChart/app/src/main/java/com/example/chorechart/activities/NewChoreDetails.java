package com.example.chorechart.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import com.example.chorechart.MainActivity;
import com.example.chorechart.R;
import com.example.chorechart.data.Chore;
import com.example.chorechart.data.Roommate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Locale;

public class NewChoreDetails extends AppCompatActivity {

    private EditText newChoreTitle, newChoreLocation, newChoreAssignee, newChoreDeadline, newChoreDescription;
    private ArrayList<Chore> choreList;
    private ArrayList<Roommate> roommates;
    private Button doneButton;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_a_new_chore_pg2);

        toolbar = findViewById(R.id.newChoreDetailstoolbar);
        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_action_back, getTheme()));
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();    // Using built-in function
        });

        doneButton = findViewById(R.id.newChoreSaveButton);
        Intent intent = getIntent();
        String message = intent.getStringExtra(AddANewChore.EXTRA_MESSAGE);
        choreList = (ArrayList<Chore>) intent.getSerializableExtra("chore_list");
        roommates = (ArrayList<Roommate>) intent.getSerializableExtra("roommates");
        newChoreTitle = findViewById(R.id.newChoreName);
        newChoreTitle.setText(message);
        newChoreLocation = findViewById(R.id.newChoreLocation);
        newChoreAssignee = findViewById(R.id.newChoreAssignee);
        newChoreDeadline = findViewById(R.id.newChoreDeadline);
        newChoreDescription = findViewById(R.id.newChoreDescription);

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = (datePicker, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(calendar);
        };

        newChoreDeadline.setOnClickListener(view -> new DatePickerDialog(NewChoreDetails.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show());

        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setSelectedItemId(R.id.navigation_add);

        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    Intent searchChoreActivityIntent = new Intent(NewChoreDetails.this, SearchChoreActivity.class);
                    searchChoreActivityIntent.putExtra("chore_list", choreList);
                    searchChoreActivityIntent.putExtra("roommates", roommates);
                    startActivity(searchChoreActivityIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.fragment_menu:
                    Intent homepageIntent = new Intent(NewChoreDetails.this, HomepageActivity.class);
                    homepageIntent.putExtra("chore_list", choreList);
                    homepageIntent.putExtra("roommates", roommates);
                    startActivity(homepageIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_roommates:
                    Intent addingRoommateActivityIntent = new Intent(NewChoreDetails.this, AddingRoommateProfileActivity.class);
                    addingRoommateActivityIntent.putExtra("chore_list", choreList);
                    addingRoommateActivityIntent.putExtra("roommates", roommates);
                    startActivity(addingRoommateActivityIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_add:
                    return true;
                case R.id.navigation_profile:
                    Intent profileActivity = new Intent(NewChoreDetails.this, ProfilePage.class);
                    profileActivity.putExtra("chore_list", choreList);
                    profileActivity.putExtra("roommates", roommates);
                    profileActivity.putExtra("roommate", roommates.get(0));
                    startActivity(profileActivity);
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        // TODO: Pass the image too
        if(message.equals("Sweep")){
            ImageView choreImg = (ImageView) findViewById(R.id.chore_details_image);
            choreImg.setImageDrawable(getDrawable(R.drawable.the_broom));
        }
        else if (message.equals("Dishes")) {
            ImageView choreImg = (ImageView) findViewById(R.id.chore_details_image);
            choreImg.setImageDrawable(getDrawable(R.drawable.cleaning_dishes));
        }
        else if (message.equals("Trash")) {
            ImageView choreImg = (ImageView) findViewById(R.id.chore_details_image);
            choreImg.setImageDrawable(getDrawable(R.drawable.the_trash));
        }
        else {
            ImageView choreImg = (ImageView) findViewById(R.id.chore_details_image);
            choreImg.setImageDrawable(getDrawable(R.drawable.question_mark_icon));
            choreImg.setBackgroundColor(Color.WHITE);
        }

        doneButton.setOnClickListener(view -> {
            if (newChoreTitle.getText().toString().isEmpty()) {
                newChoreTitle.setError("This field is required");
                newChoreTitle.requestFocus();
                return;
            }
            if (newChoreLocation.getText().toString().isEmpty()) {
                newChoreLocation.setError("This field is required");
                newChoreLocation.requestFocus();
                return;
            }
            if (newChoreAssignee.getText().toString().isEmpty()) {
                newChoreAssignee.setError("This field is required");
                newChoreAssignee.requestFocus();
                return;
            }
            if (newChoreDeadline.getText().toString().isEmpty()) {
                newChoreDeadline.setError("This field is required");
                newChoreDeadline.requestFocus();
                return;
            }
            if (newChoreDescription.getText().toString().isEmpty()) {
                newChoreDescription.setError("This field is required");
                newChoreDescription.requestFocus();
                return;
            }
            Toast.makeText(this, "The New Chore Has Been Added", Toast.LENGTH_SHORT).show();
            Chore newChore = new Chore(newChoreTitle.getText().toString(), newChoreAssignee.getText().toString(), newChoreLocation.getText().toString(), newChoreDeadline.getText().toString(), newChoreDescription.getText().toString());
            choreList.add(newChore);
            Intent homepageIntent = new Intent(NewChoreDetails.this, HomepageActivity.class);
            homepageIntent.putExtra("chore_list", choreList);
            homepageIntent.putExtra("roommates", roommates);
            startActivity(homepageIntent);
        });
    }

    private void updateLabel(Calendar calendar) {
        String format = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        newChoreDeadline.setText(dateFormat.format(calendar.getTime()));
    }
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

}