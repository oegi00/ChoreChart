package com.example.chorechart;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chorechart.activities.AddingRoommatesActivity;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private EditText userFullNameEditText;
    private Button login_screen_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        login_screen_button = findViewById(R.id.login_screen_button);
        title = findViewById(R.id.app_name);
        userFullNameEditText = findViewById(R.id.userFullName);

        // Changes the font of the title
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Chewy-Regular.ttf");
        title.setTypeface(type);

        login_screen_button.setOnClickListener(view ->  {
                String userName = userFullNameEditText.getText().toString();

                // Checks if the user entered anything in the EditText field. If not, points the user to the EditText box and displays an error message
                if (userName.isEmpty()) {
                    userFullNameEditText.setError("Name is Required");
                    userFullNameEditText.requestFocus();
                    return;
                }

                Intent addingRoommatesActivityIntent = new Intent(this, AddingRoommatesActivity.class);

                addingRoommatesActivityIntent.putExtra("userName", userName);
                startActivity(addingRoommatesActivityIntent);
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
}