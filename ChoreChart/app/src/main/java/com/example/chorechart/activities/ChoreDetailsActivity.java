package com.example.chorechart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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

import com.example.chorechart.MainActivity;
import com.example.chorechart.R;
import com.example.chorechart.data.Chore;
import com.example.chorechart.data.Roommate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        toolbar = findViewById(R.id.choreDetailsToolbar);

        title = findViewById(R.id.chore_details_title);
        location = findViewById(R.id.chore_details_location_entry);
        assignee = findViewById(R.id.chore_details_assignee_entry);
        deadline = findViewById(R.id.chore_details_deadline_entry);
        description = findViewById(R.id.chore_details_description_entry);
        save = findViewById(R.id.chore_details_save);

        Intent choreDetailsActivityIntent = getIntent();
        Chore chore = (Chore) choreDetailsActivityIntent.getSerializableExtra("chore");
        ArrayList<Roommate> roommates = (ArrayList<Roommate>) choreDetailsActivityIntent.getSerializableExtra("roommates");

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

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = (datePicker, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(calendar);
        };

        deadline.setOnClickListener(view -> new DatePickerDialog(ChoreDetailsActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show());

        // Changes the font of the toolbar title
//        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Chewy-Regular.ttf");
//        toolBarTitle.setTypeface(type);

        // Adds "Enter Your Roommate Full Name" fields when the user presses the button
//        addAnotherRoommateButton.setOnClickListener(view -> {
//            switch (numberOfRoommates) {
//                case 1:
//                    TextView roommate2TextView = findViewById(R.id.roommate2TextView);
//                    roommate2TextView.setVisibility(View.VISIBLE);
//                    roommate2.setVisibility(View.VISIBLE);
//                    numberOfRoommates++;
//                    break;
//                case 2:
//                    TextView roommate3TextView = findViewById(R.id.roommate3TextView);
//                    roommate3TextView.setVisibility(View.VISIBLE);
//                    roommate3.setVisibility(View.VISIBLE);
//                    numberOfRoommates++;
//
//                    TextView addAnotherRoommateTip = findViewById(R.id.addAnotherRoommateTip);
//                    addAnotherRoommateTip.setVisibility(View.GONE);
//                    addAnotherRoommateButton.setVisibility(View.GONE);
//                    break;
//                default:
//                    //Debugging print statement
//                    System.out.println(numberOfRoommates);
//            }
//        });
        // Sends the user to the next activity when they press Skip/Continue button
        save.setOnClickListener(view -> {
            Intent addingRoommatesActivityIntent = getIntent();
            String addingRoommatesActivityMessage = addingRoommatesActivityIntent.getStringExtra("userName");
            // To start a new Activity, uncomment the code below and enter the the name of the class to jump to. Remove this comment when finished.

//            Intent homeScreenIntent = new Intent(this, Enter the Class Name);

//            homeScreenIntent.putExtra("userName", addingRoommatesActivityMessage);
//            homeScreenIntent.putExtra("roommate1", roommate1.getText().toString());
//            homeScreenIntent.putExtra("roommate2", roommate2.getText().toString());
//            homeScreenIntent.putExtra("roommate3", roommate3.getText().toString());

//            startActivity(homeScreenIntent);
        });

        // Changes the button text from Skip to Continue or Continue to Skip based on if any text is present in EditText boxes
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
            Log.d("beforeTextChanged: ", beforeLocation);
            Log.d("beforeTextChanged: ", beforeAssignee);
            Log.d("beforeTextChanged: ", beforeDescription);
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