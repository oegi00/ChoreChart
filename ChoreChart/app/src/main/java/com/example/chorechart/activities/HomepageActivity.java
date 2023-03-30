package com.example.chorechart.activities;

import android.os.Bundle;

import com.example.chorechart.adapters.ChoreAdapter;
import com.example.chorechart.data.Chore;
import com.example.chorechart.data.Datasource;
import com.example.chorechart.databinding.ActivityHomepageBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.chorechart.R;

import java.util.ArrayList;

public class HomepageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ActivityHomepageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<Chore> myDataset = new Datasource().getChores();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new ChoreAdapter(this, myDataset));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Help Page", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}