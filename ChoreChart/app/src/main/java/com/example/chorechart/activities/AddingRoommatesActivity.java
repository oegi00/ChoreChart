package com.example.chorechart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chorechart.MainActivity;
import com.example.chorechart.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddingRoommatesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolBarTitle;
    private EditText roommate1;
    private EditText roommate2;
    private EditText roommate3;
    private FloatingActionButton addAnotherRoommateButton;
    private Button skipContinueButton;
    private int numberOfRoommates = 1;  // Tracks a number of roommates the user desires to input
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_roommates);

        toolbar = findViewById(R.id.choreChartToolbar);
        toolBarTitle = toolbar.findViewById(R.id.toolBarTitle);
        roommate1 = findViewById(R.id.roommate1);
        roommate2 = findViewById(R.id.roommate2);
        roommate3 = findViewById(R.id.roommate3);
        addAnotherRoommateButton = findViewById(R.id.addAnotherRoommateButton);
        skipContinueButton = findViewById(R.id.addingRoommateButton);

        // Adds a back button to the toolbar
        // When clicked, goes back to the previous activity
        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_action_back, getTheme()));
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();    // Using built-in function
        });

        // Changes the font of the toolbar title
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Chewy-Regular.ttf");
        toolBarTitle.setTypeface(type);

        // Adds "Enter Your Roommate Full Name" fields when the user presses the button
        addAnotherRoommateButton.setOnClickListener(view -> {
            switch (numberOfRoommates) {
                case 1:
                    TextView roommate2TextView = findViewById(R.id.roommate2TextView);
                    roommate2TextView.setVisibility(View.VISIBLE);
                    roommate2.setVisibility(View.VISIBLE);
                    numberOfRoommates++;
                    break;
                case 2:
                    TextView roommate3TextView = findViewById(R.id.roommate3TextView);
                    roommate3TextView.setVisibility(View.VISIBLE);
                    roommate3.setVisibility(View.VISIBLE);
                    numberOfRoommates++;

                    TextView addAnotherRoommateTip = findViewById(R.id.addAnotherRoommateTip);
                    addAnotherRoommateTip.setVisibility(View.GONE);
                    addAnotherRoommateButton.setVisibility(View.GONE);
                    break;
                default:
                    //Debugging print statement
                    System.out.println(numberOfRoommates);
            }
        });
        // Sends the user to the next activity when they press Skip/Continue button
        skipContinueButton.setOnClickListener(view -> {
            Intent addingRoommatesActivityIntent = getIntent();
            String addingRoommatesActivityMessage = addingRoommatesActivityIntent.getStringExtra("userName");

            // TODO To start a new Activity, uncomment the code below and enter the name of the class to jump to.

//            Intent homeScreenIntent = new Intent(this, Enter the Class Name);
//
//            homeScreenIntent.putExtra("userName", addingRoommatesActivityMessage);
//            homeScreenIntent.putExtra("roommate1", roommate1.getText().toString());
//            homeScreenIntent.putExtra("roommate2", roommate2.getText().toString());
//            homeScreenIntent.putExtra("roommate3", roommate3.getText().toString());
//
//            startActivity(homeScreenIntent);
        });

        // Changes the button text from Skip to Continue or Continue to Skip based on if any text is present in EditText boxes
        // Utilizes InputValidator class
        roommate1.addTextChangedListener(new InputValidator());
        roommate2.addTextChangedListener(new InputValidator());
        roommate3.addTextChangedListener(new InputValidator());
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

    // Changes the button text
    private class InputValidator implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (roommate1.getText().toString().isEmpty() && roommate2.getText().toString().isEmpty() && roommate3.getText().toString().isEmpty()) {
                skipContinueButton.setText("Skip");
            }
            else {
                skipContinueButton.setText("Continue");
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}