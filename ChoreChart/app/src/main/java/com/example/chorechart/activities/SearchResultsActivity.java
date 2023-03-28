package com.example.chorechart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.chorechart.R;
import com.example.chorechart.adapters.SearchResultsRecyclerAdapter;
import com.example.chorechart.data.Chore;
import com.example.chorechart.data.Roommate;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<Chore> choreList;
    private ArrayList<Roommate> roommates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        toolbar = findViewById(R.id.searchResultsToolbar);
        recyclerView = findViewById(R.id.search_results_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent searchResultsActivityIntent = getIntent();

        choreList = (ArrayList<Chore>) searchResultsActivityIntent.getSerializableExtra("choreList");   // Retrieves the chore list from the previous activity
        Chore choreToFind = (Chore) searchResultsActivityIntent.getSerializableExtra("choreToFind");    //  Retrieves the chore to find from the previous activity
        roommates = (ArrayList<Roommate>) searchResultsActivityIntent.getSerializableExtra("roommates");
        ArrayList<Chore> searchResults = findChores(choreToFind);

        // Adds a back button to the toolbar
        // When clicked, goes back to the previous activity
        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_action_back, getTheme()));
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();    // Using built-in function
        });

        SearchResultsRecyclerAdapter adapter = new SearchResultsRecyclerAdapter(this, searchResults, roommates);
        recyclerView.setAdapter(adapter);

        // Implements the swipe to remove feature to the recycler view
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getBindingAdapterPosition();
                SearchResultsRecyclerAdapter searchResultsAdapter = (SearchResultsRecyclerAdapter) recyclerView.getAdapter();

                ArrayList<Chore> rChoreList = searchResultsAdapter.getChoreList();
                rChoreList.remove(position);
                searchResultsAdapter.setChoreList(rChoreList);
                adapter.notifyItemRemoved(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    // Finds chores using information entered by the user
    // This function is not perfect. There might be bugs in here
    private ArrayList<Chore> findChores(Chore choreToFind) {
        ArrayList<Chore> searchResults = new ArrayList<>();

        if (choreToFind.getChoreName() == null) {
            if (choreToFind.getAssignee() == null) {
                if (choreToFind.getLocation() == null) {
                    if (choreToFind.getDeadline() == null) {
                        return choreList;
                    }
                }
            }
        }

        for (int i = 0; i < choreList.size(); i++) {
            if (choreList.get(i).getChoreName().equalsIgnoreCase(choreToFind.getChoreName()) && choreList.get(i).getAssignee().equalsIgnoreCase(choreToFind.getAssignee()) && choreList.get(i).getLocation().equalsIgnoreCase(choreToFind.getLocation()) && choreList.get(i).getDeadline().equalsIgnoreCase(choreToFind.getDeadline())) {
                searchResults.clear();
                searchResults.add(choreList.get(i));
                break;
            }
            else if (choreList.get(i).getChoreName().equalsIgnoreCase(choreToFind.getChoreName()) && choreList.get(i).getAssignee().equalsIgnoreCase(choreToFind.getAssignee()) && choreList.get(i).getLocation().equalsIgnoreCase(choreToFind.getLocation()) || choreList.get(i).getDeadline().equalsIgnoreCase(choreToFind.getDeadline())) {
                searchResults.add(choreList.get(i));
            }
            else if (choreList.get(i).getChoreName().equalsIgnoreCase(choreToFind.getChoreName()) && choreList.get(i).getAssignee().equalsIgnoreCase(choreToFind.getAssignee()) || choreList.get(i).getLocation().equalsIgnoreCase(choreToFind.getLocation()) && choreList.get(i).getDeadline().equalsIgnoreCase(choreToFind.getDeadline())) {
                searchResults.add(choreList.get(i));
            }
            else if (choreList.get(i).getChoreName().equalsIgnoreCase(choreToFind.getChoreName()) || choreList.get(i).getAssignee().equalsIgnoreCase(choreToFind.getAssignee()) && choreList.get(i).getLocation().equalsIgnoreCase(choreToFind.getLocation()) && choreList.get(i).getDeadline().equalsIgnoreCase(choreToFind.getDeadline())) {
                searchResults.add(choreList.get(i));
            }
            else if (choreList.get(i).getChoreName().equalsIgnoreCase(choreToFind.getChoreName()) && choreList.get(i).getAssignee().equalsIgnoreCase(choreToFind.getAssignee()) || choreList.get(i).getLocation().equalsIgnoreCase(choreToFind.getLocation()) || choreList.get(i).getDeadline().equalsIgnoreCase(choreToFind.getDeadline())) {
                searchResults.add(choreList.get(i));
            }
            else if (choreList.get(i).getChoreName().equalsIgnoreCase(choreToFind.getChoreName()) || choreList.get(i).getAssignee().equalsIgnoreCase(choreToFind.getAssignee()) && choreList.get(i).getLocation().equalsIgnoreCase(choreToFind.getLocation()) || choreList.get(i).getDeadline().equalsIgnoreCase(choreToFind.getDeadline())) {
                searchResults.add(choreList.get(i));
            }
            else if (choreList.get(i).getChoreName().equalsIgnoreCase(choreToFind.getChoreName()) || choreList.get(i).getAssignee().equalsIgnoreCase(choreToFind.getAssignee()) || choreList.get(i).getLocation().equalsIgnoreCase(choreToFind.getLocation()) && choreList.get(i).getDeadline().equalsIgnoreCase(choreToFind.getDeadline())) {
                searchResults.add(choreList.get(i));
            }
        }

        return searchResults;
    }
}