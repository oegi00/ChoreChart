package com.example.chorechart.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chorechart.MainActivity;
import com.example.chorechart.R;

public class NewChoreDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_a_new_chore_pg2);

        // add title to the new chore
        Intent intent = getIntent();
        String message = intent.getStringExtra(AddANewChore.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.chore_details_title);
        textView.setText(message);

        // TODO: Pass the image too
        if(message == "Sweep"){
            ImageView choreImg = (ImageView) findViewById(R.id.chore_details_image);
            //choreImg.setImageDrawable();
        }
    }

    // TODO: go back to the homescreen
    public void doneButton(View view){
        // the two lines below are for transitioning to the homepage
        //Intent intent = new Intent(this, "homepage.class");
        //startActivity(intent);
    }

    // TODO: How do you briefly add a "New chore added" notification


}