package com.example.chorechart.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chorechart.R;
import com.example.chorechart.activities.HomepageActivity;
import com.example.chorechart.data.Chore;

import java.util.ArrayList;

public class ChoreAdapter extends RecyclerView.Adapter<ChoreAdapter.ChoreViewHolder> {
    private Context context;
    HomepageActivity homepageActivity;
    private ArrayList<Chore> dataset;

    public ChoreAdapter(Context context, ArrayList<Chore> list) {
        dataset = list;
        this.context = context;
        this.homepageActivity = homepageActivity;
    }

    @NonNull
    @Override
    public ChoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ChoreViewHolder(adapterLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ChoreViewHolder holder, int position) {
        Chore item = dataset.get(position);
        holder.textView.setText(item.getChoreName());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    class ChoreViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ChoreViewHolder(View view){
            super(view);
            textView = view.findViewById((R.id.item_title));
        }
    }
}
