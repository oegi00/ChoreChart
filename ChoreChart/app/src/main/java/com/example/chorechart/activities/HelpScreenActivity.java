package com.example.chorechart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;

import com.example.chorechart.R;

public class HelpScreenActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);

        toolbar = findViewById(R.id.choreChartHelpToolbar);

        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_action_back, getTheme()));
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();    // Using built-in function
        });

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("    Chore Chart Help");

        //actionBar.setDisplayHomeAsUpEnabled(true);
    }
}