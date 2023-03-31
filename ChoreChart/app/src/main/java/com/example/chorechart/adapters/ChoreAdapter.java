package com.example.chorechart.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chorechart.R;
import com.example.chorechart.activities.HomepageActivity;
import com.example.chorechart.activities.ProfilePage;
import com.example.chorechart.data.Chore;
import com.example.chorechart.data.Roommate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ChoreAdapter extends RecyclerView.Adapter<ChoreAdapter.ChoreViewHolder> {
    private Context context;
    private ArrayList<Chore> choreList;
    private ArrayList<Roommate> roommates;

    public ChoreAdapter(Context context, ArrayList<Chore> list, ArrayList<Roommate> roommates) {
        this.choreList = list;
        this.context = context;
        this.roommates = roommates;
    }

    @NonNull
    @Override
    public ChoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.homepage_card_layout, null);
        return new ChoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChoreViewHolder holder, int position) {
        Chore chore = choreList.get(position);

        holder.choreName.setText(chore.getChoreName());
        holder.deadline.setText("Due: " + chore.getDeadline());
    }

    @Override
    public int getItemCount() {
        return choreList.size();
    }

    public ArrayList<Chore> getChoreList() {
        return choreList;
    }

    class ChoreViewHolder extends RecyclerView.ViewHolder {
        TextView choreName, deadline;
        FloatingActionButton doneFab;
        ImageButton profileButton;
        public ChoreViewHolder(@NonNull View itemView){
            super(itemView);
            choreName = itemView.findViewById(R.id.homescreen_chore_title);
            deadline = itemView.findViewById(R.id.homescreen_chore_deadline);
            doneFab = itemView.findViewById(R.id.homescreen_chore_completed);
            profileButton = itemView.findViewById(R.id.homescreen_profile_button);

            doneFab.setOnClickListener(view -> {
                int chorePosition = getAbsoluteAdapterPosition();

                choreList.remove(chorePosition);
                notifyItemRemoved(chorePosition);
            });

            profileButton.setOnClickListener(view -> {
                Intent intent = new Intent(context, ProfilePage.class);
                int chorePosition = getAbsoluteAdapterPosition();

                for (int i = 0; i < roommates.size(); i++) {
                    if (choreList.get(chorePosition).getAssignee().equals(roommates.get(i).getName())) {
                        intent.putExtra("roommates", roommates);
                        intent.putExtra("chore_list", choreList);
                        intent.putExtra("roommate", roommates.get(i));
                        context.startActivity(intent);
                        break;
                    }
                }
            });

            itemView.setOnClickListener(view -> {

            });
        }
    }
}
