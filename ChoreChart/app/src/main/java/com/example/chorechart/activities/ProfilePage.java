package com.example.chorechart.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import com.example.chorechart.R;
import com.example.chorechart.data.Chore;
import com.example.chorechart.data.Roommate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ProfilePage extends AppCompatActivity {

    private EditText name;
    private EditText bio;
    private Button button;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        name = findViewById(R.id.editTextTextPersonName2);
        bio = findViewById(R.id.editTextTextPersonName4);
        button = findViewById(R.id.profile_save_button);
        toolbar = findViewById(R.id.profileToolbar);

        // Adds a back button to the toolbar
        // When clicked, goes back to the previous activity
        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_action_back, getTheme()));
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();    // Using built-in function
        });

        Intent intent = getIntent();
        Roommate roommate = (Roommate) intent.getSerializableExtra("roommate");
        ArrayList<Roommate> roommates = (ArrayList<Roommate>) intent.getSerializableExtra("roommates");
        ArrayList<Chore> choreList = (ArrayList<Chore>) intent.getSerializableExtra("chore_list");

        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setSelectedItemId(R.id.navigation_profile);

        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    Intent searchChoreActivityIntent = new Intent(ProfilePage.this, SearchChoreActivity.class);
                    searchChoreActivityIntent.putExtra("chore_list", choreList);
                    searchChoreActivityIntent.putExtra("roommates", roommates);
                    startActivity(searchChoreActivityIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.fragment_menu:
                    Intent homepageIntent = new Intent(ProfilePage.this, HomepageActivity.class);
                    homepageIntent.putExtra("chore_list", choreList);
                    homepageIntent.putExtra("roommates", roommates);
                    startActivity(homepageIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_roommates:
                    Intent addingRoommateActivityIntent = new Intent(ProfilePage.this, AddingRoommateProfileActivity.class);
                    addingRoommateActivityIntent.putExtra("chore_list", choreList);
                    addingRoommateActivityIntent.putExtra("roommates", roommates);
                    startActivity(addingRoommateActivityIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_add:
                    Intent newChoreIntent = new Intent(ProfilePage.this, AddANewChore.class);
                    newChoreIntent.putExtra("chore_list", choreList);
                    newChoreIntent.putExtra("roommates", roommates);
                    startActivity(newChoreIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_profile:
                    return true;
            }
            return false;
        });

        name.setText(roommate.getName());
        bio.setText(roommate.getBio());

        button.setOnClickListener(view -> {
            for (int i = 0; i < roommates.size(); i++) {
                if (roommates.get(i).getName().equals(roommate.getName())) {
                    roommates.get(i).setName(name.getText().toString());
                    roommates.get(i).setBio(bio.getText().toString());
                }
            }
            Intent homescreenIntent = new Intent(this, HomepageActivity.class);
            homescreenIntent.putExtra("roommates", roommates);
            homescreenIntent.putExtra("chore_list", choreList);

            startActivity(homescreenIntent);
        });
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