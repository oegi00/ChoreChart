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
import android.widget.Toast;

import com.example.chorechart.MainActivity;
import com.example.chorechart.R;
import com.example.chorechart.data.Chore;
import com.example.chorechart.data.Roommate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddingRoommateProfileActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private ArrayList<Chore> choreList;
    private ArrayList<Roommate> roommates;
    private EditText roommateName;
    private EditText roommateBio;
    private Button enterButton, cancelButton, removeRoommateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_roommate_profile);

        toolbar = findViewById(R.id.addRoommateToolbar);
        roommateName = findViewById(R.id.enterRoommateProfileName);
        roommateBio = findViewById(R.id.roomateProfileTextBox);
        enterButton = findViewById(R.id.button);
        cancelButton = findViewById(R.id.button2);
        removeRoommateButton = findViewById(R.id.button3);

        // Adds a back button to the toolbar
        // When clicked, goes back to the previous activity
        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_action_back, getTheme()));
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();    // Using built-in function
        });

        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setSelectedItemId(R.id.navigation_roommates);

        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    Intent searchChoreActivityIntent = new Intent(AddingRoommateProfileActivity.this, SearchChoreActivity.class);
                    searchChoreActivityIntent.putExtra("chore_list", choreList);
                    searchChoreActivityIntent.putExtra("roommates", roommates);
                    startActivity(searchChoreActivityIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.fragment_menu:
                    Intent homepageIntent = new Intent(AddingRoommateProfileActivity.this, HomepageActivity.class);
                    homepageIntent.putExtra("chore_list", choreList);
                    homepageIntent.putExtra("roommates", roommates);
                    startActivity(homepageIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_roommates:
                    return true;
                case R.id.navigation_add:
                    Intent addNewChoreIntent = new Intent(AddingRoommateProfileActivity.this, AddANewChore.class);
                    addNewChoreIntent.putExtra("chore_list", choreList);
                    addNewChoreIntent.putExtra("roommates", roommates);
                    startActivity(addNewChoreIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_profile:
                    Intent profileActivity = new Intent(AddingRoommateProfileActivity.this, ProfilePage.class);
                    profileActivity.putExtra("chore_list", choreList);
                    profileActivity.putExtra("roommates", roommates);
                    profileActivity.putExtra("roommate", roommates.get(0));
                    startActivity(profileActivity);
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        Intent addingRoommateProfileActivity = getIntent();
        choreList = (ArrayList<Chore>) addingRoommateProfileActivity.getSerializableExtra("chore_list");
        roommates = (ArrayList<Roommate>) addingRoommateProfileActivity.getSerializableExtra("roommates");

        enterButton.setOnClickListener(view -> {
            String name = roommateName.getText().toString();
            String bio = roommateBio.getText().toString();

            if (name.isEmpty()) {
                roommateName.setError("Name is Required");
                roommateName.requestFocus();
                return;
            }

            if (bio.isEmpty()) {
                roommateBio.setError("Bio is Required");
                roommateBio.requestFocus();
                return;
            }

            if (roommates.size() == 4) {
                Toast.makeText(this, "The maximum number of roommates has been reached", Toast.LENGTH_LONG).show();
                return;
            }

            Roommate newRoommate = new Roommate(name, bio);
            roommates.add(newRoommate);

            Intent homepageIntent = new Intent(this, HomepageActivity.class);
            homepageIntent.putExtra("chore_list", choreList);
            homepageIntent.putExtra("roommates", roommates);
            startActivity(homepageIntent);
        });

        cancelButton.setOnClickListener(view -> {
            Intent homepageIntent = new Intent(this, HomepageActivity.class);
            homepageIntent.putExtra("chore_list", choreList);
            homepageIntent.putExtra("roommates", roommates);
            startActivity(homepageIntent);
        });

        removeRoommateButton.setOnClickListener(view -> {
            String name = roommateName.getText().toString();

            if (roommates.size() == 1) {
                Toast.makeText(this, "No Roommates to Remove", Toast.LENGTH_LONG).show();
                return;
            }

            if (name.isEmpty()) {
                roommateName.setError("Name is Required");
                roommateName.requestFocus();
                return;
            }

            if (name.equalsIgnoreCase(roommates.get(0).getName())) {
                roommateName.setError("You cannot remove the host");
                roommateName.requestFocus();
                return;
            }

            for (int i = 1; i < roommates.size(); i++) {
                if (roommates.get(i).getName().equalsIgnoreCase(name)) {
                    roommates.remove(i);
                    Intent homepageIntent = new Intent(AddingRoommateProfileActivity.this, HomepageActivity.class);
                    homepageIntent.putExtra("chore_list", choreList);
                    homepageIntent.putExtra("roommates", roommates);
                    startActivity(homepageIntent);
                    return;
                }
            }

            Toast.makeText(this, name + " does not exist", Toast.LENGTH_SHORT).show();

        });
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
}
