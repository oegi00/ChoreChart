package com.example.chorechart.data;

import com.example.chorechart.R;
import com.example.chorechart.data.Chore;

import java.util.ArrayList;

public class Datasource {

    ArrayList<Chore> chores = new ArrayList<>();

    public ArrayList<Chore> getChores(){
        chores.add(new Chore(Integer.toString(R.string.chore1), "roommate", "general", "never", "filler"));
        chores.add(new Chore(Integer.toString(R.string.chore2), "roommate", "general", "never", "filler"));
        chores.add(new Chore(Integer.toString(R.string.chore3), "roommate", "general", "never", "filler"));
        chores.add(new Chore(Integer.toString(R.string.chore4), "roommate", "general", "never", "filler"));
        chores.add(new Chore(Integer.toString(R.string.chore5), "roommate", "general", "never", "filler"));
        chores.add(new Chore(Integer.toString(R.string.chore6), "roommate", "general", "never", "filler"));
        chores.add(new Chore(Integer.toString(R.string.chore7), "roommate", "general", "never", "filler"));
        chores.add(new Chore(Integer.toString(R.string.chore8), "roommate", "general", "never", "filler"));
        chores.add(new Chore(Integer.toString(R.string.chore9), "roommate", "general", "never", "filler"));
        chores.add(new Chore(Integer.toString(R.string.chore10), "roommate", "general", "never", "filler"));
        return chores;
    }
}
