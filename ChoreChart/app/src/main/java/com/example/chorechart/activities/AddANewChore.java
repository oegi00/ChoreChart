package com.example.chorechart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chorechart.R;

public class AddANewChore extends AppCompatActivity  {
    public static final String EXTRA_MESSAGE = "ProfilePage.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_a_new_chore_pg1);
    }

    // Function for the Sweep Button
    public void sweepButton(View view){
        Intent intent = new Intent(this, NewChoreDetails.class);
        intent.putExtra(EXTRA_MESSAGE, "Sweep");
        startActivity(intent);

        // NOTE* How do I also add the photo?
    }

    // Function for the Dishes Button
    public void dishesButton(View view){
        Intent intent = new Intent(this, NewChoreDetails.class);
        intent.putExtra(EXTRA_MESSAGE, "Dishes");
        startActivity(intent);
    }

    // Function for the Trash Button
    public void trashButton(View view){
        Intent intent = new Intent(this, NewChoreDetails.class);
        intent.putExtra(EXTRA_MESSAGE, "Trash");
        startActivity(intent);
    }

    // Function for the Create your own Button
    public void createYourOwnButton(View view){
        Intent intent = new Intent(this, NewChoreDetails.class);
        // add something that'll have them customize
        startActivity(intent);
    }

    // TODO: Add a back button to the homepage OR add navigation bar

}
