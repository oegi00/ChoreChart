package com.example.chorechart.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.chorechart.adapters.ChoreAdapter;
import com.example.chorechart.data.Chore;
import com.example.chorechart.data.Datasource;
import com.example.chorechart.data.Roommate;
import com.example.chorechart.databinding.ActivityHomepageBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chorechart.R;

import java.util.ArrayList;

public class HomepageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ActivityHomepageBinding binding;
    private ImageButton userImg, roommate1Img, roommate2Img, roommate3Img, helpButton;
    private TextView userText, roommate1Text, roommate2Text, roommate3Text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();

        helpButton = findViewById(R.id.homescreen_help_button);
        helpButton.setOnClickListener(view -> {
            Intent helpScreenIntent = new Intent(this, HelpScreenActivity.class);
            startActivity(helpScreenIntent);
        });
        userImg = binding.homescreenUserImage;
        roommate1Img = binding.homescreenRoommate1Image;
        roommate2Img = binding.homescreenRoommate2Image;
        roommate3Img = binding.homescreenRoommate3Image;
        userText = binding.homescreenUserName;
        roommate1Text = binding.homescreenRoommate1Name;
        roommate2Text = binding.homescreenRoommate2Name;
        roommate3Text = binding.homescreenRoommate3Name;

        ArrayList<Roommate> roommates = (ArrayList<Roommate>) intent.getSerializableExtra("roommates");
        ArrayList<Chore> choreList = (ArrayList<Chore>) intent.getSerializableExtra("chore_list");
        userText.setText(roommates.get(0).getName());

        if (roommates.size() >= 2)  {
            roommate1Img.setVisibility(View.VISIBLE);
            roommate1Text.setVisibility(View.VISIBLE);
            roommate1Text.setText(roommates.get(1).getName());
        }
        if (roommates.size() >= 3) {
            roommate2Img.setVisibility(View.VISIBLE);
            roommate2Text.setVisibility(View.VISIBLE);
            roommate2Text.setText(roommates.get(2).getName());
        }
        if (roommates.size() == 4) {
            roommate3Img.setVisibility(View.VISIBLE);
            roommate3Text.setVisibility(View.VISIBLE);
            roommate3Text.setText(roommates.get(3).getName());
        }

        recyclerView = findViewById(R.id.homescreen_recycler_view);
        recyclerView.setAdapter(new ChoreAdapter(this, choreList, roommates));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setSelectedItemId(R.id.fragment_menu);
        navigationView.setOnItemSelectedListener(item -> {
            ChoreAdapter choreAdapter = (ChoreAdapter) recyclerView.getAdapter();
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    Intent searchChoreActivityIntent = new Intent(HomepageActivity.this, SearchChoreActivity.class);
                    searchChoreActivityIntent.putExtra("chore_list", choreAdapter.getChoreList());
                    searchChoreActivityIntent.putExtra("roommates", roommates);
                    startActivity(searchChoreActivityIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.fragment_menu:
                    return true;
                case R.id.navigation_roommates:
                    Intent addingRoommateActivityIntent = new Intent(HomepageActivity.this, AddingRoommateProfileActivity.class);
                    addingRoommateActivityIntent.putExtra("chore_list", choreAdapter.getChoreList());
                    addingRoommateActivityIntent.putExtra("roommates", roommates);
                    startActivity(addingRoommateActivityIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_add:
                    Intent addChoreActivity = new Intent(HomepageActivity.this, AddANewChore.class);
                    addChoreActivity.putExtra("chore_list", choreAdapter.getChoreList());
                    addChoreActivity.putExtra("roommates", roommates);
                    startActivity(addChoreActivity);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_profile:
                    Intent profileActivity = new Intent(HomepageActivity.this, ProfilePage.class);
                    profileActivity.putExtra("chore_list", choreAdapter.getChoreList());
                    profileActivity.putExtra("roommates", roommates);
                    profileActivity.putExtra("roommate", roommates.get(0));
                    startActivity(profileActivity);
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        Intent profilePageIntent = new Intent(this, ProfilePage.class);
        profilePageIntent.putExtra("roommates", roommates);

        userImg.setOnClickListener(view -> {
            ChoreAdapter choreAdapter = (ChoreAdapter) recyclerView.getAdapter();
            profilePageIntent.putExtra("chore_list", choreAdapter.getChoreList());
            profilePageIntent.putExtra("roommate", roommates.get(0));
            startActivity(profilePageIntent);
        });
        roommate1Img.setOnClickListener(view -> {
            ChoreAdapter choreAdapter = (ChoreAdapter) recyclerView.getAdapter();
            profilePageIntent.putExtra("chore_list", choreAdapter.getChoreList());
            profilePageIntent.putExtra("roommate", roommates.get(1));
            startActivity(profilePageIntent);
        });
        roommate2Img.setOnClickListener(view -> {
            ChoreAdapter choreAdapter = (ChoreAdapter) recyclerView.getAdapter();
            profilePageIntent.putExtra("chore_list", choreAdapter.getChoreList());
            profilePageIntent.putExtra("roommate", roommates.get(2));
            startActivity(profilePageIntent);
        });
        roommate3Img.setOnClickListener(view -> {
            ChoreAdapter choreAdapter = (ChoreAdapter) recyclerView.getAdapter();
            profilePageIntent.putExtra("chore_list", choreAdapter.getChoreList());
            profilePageIntent.putExtra("roommate", roommates.get(3));
            startActivity(profilePageIntent);
        });
    }
}