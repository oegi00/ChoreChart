package com.example.chorechart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import com.example.chorechart.R;
import com.example.chorechart.data.Chore;
import com.example.chorechart.data.Roommate;

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



    // TODO: take in the roommate's name and bio : Have a default profile pic.

}