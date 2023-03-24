package com.example.chorechart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.chorechart.R;

import java.util.ArrayList;
import java.util.Locale;

public class SearchChoreActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private final Calendar calendar = Calendar.getInstance();
    private EditText choreName;
    private Spinner assignee;
    private EditText location;
    private EditText deadline;
    private Button searchButton;
    private int dropdownPosition = 0;   // Tracks the selected position of the dropdown menu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_chore);

        toolbar = findViewById(R.id.searchScreenToolbar);
        choreName = findViewById(R.id.searchChoreNameEditText);
        assignee = findViewById(R.id.searchAssigneeSpinner);
        location = findViewById(R.id.searchLocationEditView);
        deadline = findViewById(R.id.searchDeadlineEditText);
        searchButton = findViewById(R.id.searchChoreButton);

        // TODO uncomment the following line
        //Intent searchChoreActivityIntent = getIntent();
        ArrayList<String> names = new ArrayList<>();
        names.add("Select assignee");

        // TODO uncomment next lines
//        names.add(searchChoreActivityIntent.getStringExtra("userName"));
//        String roommate1 = searchChoreActivityIntent.getStringExtra("roommate1");
//        String roommate2 = searchChoreActivityIntent.getStringExtra("roommate2");
//        String roommate3 = searchChoreActivityIntent.getStringExtra("roommate3");

//        if (!roommate1.isEmpty()) { names.add(roommate1); }
//        if (!roommate2.isEmpty()) { names.add(roommate2); }
//        if (!roommate3.isEmpty()) { names.add(roommate3); }

        ArrayAdapter<String> assigneeAdapter = new ArrayAdapter<>(this, R.layout.spinner__dropdown_item, names);
        assignee.setAdapter(assigneeAdapter);

        // Adds a back button to the toolbar
        // When clicked, goes back to the previous activity
        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_action_back, getTheme()));
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();    // Using built-in function
        });

        // Creates a Calendar popup
        // Utilizes updateLabel function
        DatePickerDialog.OnDateSetListener date = (datePicker, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        // Gets the position of the selected item in the dropdown menu
        assignee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                dropdownPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Displays the Calendar popup when the user taps on the "Deadline" box
        deadline.setOnClickListener(view -> new DatePickerDialog(SearchChoreActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show());

        searchButton.setOnClickListener(view -> {
            Intent searchResultsActivityIntent = new Intent(SearchChoreActivity.this, SearchResultsActivity.class);

            searchResultsActivityIntent.putExtra("name", choreName.getText().toString());
            searchResultsActivityIntent.putExtra("assignee", names.get(dropdownPosition));
            searchResultsActivityIntent.putExtra("location", location.getText().toString());
            searchResultsActivityIntent.putExtra("deadline", deadline.getText().toString());

            startActivity(searchResultsActivityIntent);
        });
    }

    // Removes virtual keyboard from the screen when the user taps anywhere on the screen except the EditText box
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

    // Sets the date in the "Deadline" box according to the specified format
    private void updateLabel() {
        String format = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        deadline.setText(dateFormat.format(calendar.getTime()));
    }
}