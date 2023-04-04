package com.example.chorechart;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HelpScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("    Chore Chart Help");

        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    // methods to control the operations that will
    // happen when user clicks on the action buttons
//    @Override
//    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {
//
//        switch (item.getItemId()){
//            case R.id.search:
//                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.refresh:
//                Toast.makeText(this, "Refresh Clicked", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.copy:
//                Toast.makeText(this, "Copy Clicked", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}