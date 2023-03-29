package com.example.chorechart.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chorechart.R;
import com.example.chorechart.activities.ChoreDetailsActivity;
import com.example.chorechart.activities.SearchChoreActivity;
import com.example.chorechart.data.Chore;
import com.example.chorechart.data.Roommate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SearchResultsRecyclerAdapter extends RecyclerView.Adapter<SearchResultsRecyclerAdapter.SearchResultsViewHolder> {

    private Context context;
    private ArrayList<Chore> choreList;
    private ArrayList<Roommate> roommates;

    public SearchResultsRecyclerAdapter(Context context, ArrayList<Chore> choreList, ArrayList<Roommate> roommates) {
        this.context = context;
        this.choreList = choreList;
        this.roommates = roommates;
    }

    @NonNull
    @Override
    public SearchResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_results_card_layout, null);
        return new SearchResultsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultsViewHolder holder, int position) {
        Chore chore = choreList.get(position);

        holder.title.setText(chore.getChoreName());
        holder.assignee.setText(chore.getAssignee());
        holder.location.setText(chore.getLocation());
        holder.deadline.setText(chore.getDeadline());
        holder.description.setText(chore.getDescription());
    }

    @Override
    public int getItemCount() {
        return choreList.size();
    }

    public ArrayList<Chore> getChoreList() {
        return choreList;
    }

    public void setChoreList(ArrayList<Chore> choreList) {
        this.choreList = choreList;
    }

    class SearchResultsViewHolder extends RecyclerView.ViewHolder {

        TextView title, assignee, location, deadline, description;
        FloatingActionButton doneButton;
        public SearchResultsViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.card_layout_chore_name);
            assignee = itemView.findViewById(R.id.card_layout_search_assignee);
            location = itemView.findViewById(R.id.card_layout_search_location);
            deadline = itemView.findViewById(R.id.card_layout_search_deadline);
            description = itemView.findViewById(R.id.card_layout_search_description);
            doneButton = itemView.findViewById(R.id.card_layout_done_button);

            itemView.setOnClickListener(view -> {
                int chorePosition = getOnChoreClickedPosition();
                Intent intent = new Intent(context, ChoreDetailsActivity.class);

                intent.putExtra("chore", choreList.get(chorePosition));
                intent.putExtra("roommates", roommates);
                context.startActivity(intent);
            });

            doneButton.setOnClickListener(view -> {
                int chorePosition = getOnChoreClickedPosition();

                choreList.remove(chorePosition);
                notifyItemRemoved(chorePosition);
            });
        }

        // Gets the position of the item a user clicked on
        // This method is necessary to correctly remove an item from the array list
        public int getOnChoreClickedPosition() {
            Chore tempChore = new Chore(title.getText().toString(), assignee.getText().toString(), location.getText().toString(), deadline.getText().toString(), description.getText().toString());

            for (int i = 0; i < choreList.size(); i++) {
                if (tempChore.compareChores(choreList.get(i))){
                    return i;
                }
            }

            return 0;
        }
    }
}
