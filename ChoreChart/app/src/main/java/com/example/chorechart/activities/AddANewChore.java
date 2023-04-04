package com.example.chorechart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import com.example.chorechart.R;
import com.example.chorechart.adapters.ChoreAdapter;
import com.example.chorechart.data.Chore;
import com.example.chorechart.data.Roommate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AddANewChore extends AppCompatActivity  {
    public static final String EXTRA_MESSAGE = "ProfilePage.MESSAGE";
    private ArrayList<Chore> choreList;
    private ArrayList<Roommate> roommates;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_a_new_chore_pg1);

        toolbar = findViewById(R.id.addNewChoreToolbar);

        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_action_back, getTheme()));
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();    // Using built-in function
        });

        Intent addNewChoreIntent = getIntent();
        choreList = (ArrayList<Chore>) addNewChoreIntent.getSerializableExtra("chore_list");
        roommates = (ArrayList<Roommate>) addNewChoreIntent.getSerializableExtra("roommates");

        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setSelectedItemId(R.id.navigation_add);

        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    Intent searchChoreActivityIntent = new Intent(AddANewChore.this, SearchChoreActivity.class);
                    searchChoreActivityIntent.putExtra("chore_list", choreList);
                    searchChoreActivityIntent.putExtra("roommates", roommates);
                    startActivity(searchChoreActivityIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.fragment_menu:
                    Intent homepageIntent = new Intent(AddANewChore.this, HomepageActivity.class);
                    homepageIntent.putExtra("chore_list", choreList);
                    homepageIntent.putExtra("roommates", roommates);
                    startActivity(homepageIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_roommates:
                    Intent addingRoommateActivityIntent = new Intent(AddANewChore.this, AddingRoommateProfileActivity.class);
                    addingRoommateActivityIntent.putExtra("chore_list", choreList);
                    addingRoommateActivityIntent.putExtra("roommates", roommates);
                    startActivity(addingRoommateActivityIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_add:
                    return true;
                case R.id.navigation_profile:
                    Intent profileActivity = new Intent(AddANewChore.this, ProfilePage.class);
                    profileActivity.putExtra("chore_list", choreList);
                    profileActivity.putExtra("roommates", roommates);
                    profileActivity.putExtra("roommate", roommates.get(0));
                    startActivity(profileActivity);
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });
    }

    // Function for the Sweep Button
    public void sweepButton(View view){
        Intent intent = new Intent(this, NewChoreDetails.class);
        intent.putExtra("chore_list", choreList);
        intent.putExtra("roommates", roommates);
        intent.putExtra(EXTRA_MESSAGE, "Sweep");
        startActivity(intent);

        // NOTE* How do I also add the photo?
    }

    // Function for the Dishes Button
    public void dishesButton(View view){
        Intent intent = new Intent(this, NewChoreDetails.class);
        intent.putExtra("chore_list", choreList);
        intent.putExtra("roommates", roommates);
        intent.putExtra(EXTRA_MESSAGE, "Dishes");
        startActivity(intent);
    }

    // Function for the Trash Button
    public void trashButton(View view){
        Intent intent = new Intent(this, NewChoreDetails.class);
        intent.putExtra("chore_list", choreList);
        intent.putExtra("roommates", roommates);
        intent.putExtra(EXTRA_MESSAGE, "Trash");
        startActivity(intent);
    }

    // Function for the Create your own Button
    public void createYourOwnButton(View view){
        Intent intent = new Intent(this, NewChoreDetails.class);
        intent.putExtra("chore_list", choreList);
        intent.putExtra("roommates", roommates);
        intent.putExtra(EXTRA_MESSAGE, "");
        startActivity(intent);
    }

    // TODO: Add a back button to the homepage OR add navigation bar

}
