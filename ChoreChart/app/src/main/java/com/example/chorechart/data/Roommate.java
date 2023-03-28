package com.example.chorechart.data;

import java.io.Serializable;

public class Roommate implements Serializable {

    private String name;
    private String bio;

    public Roommate() {
        this.name = null;
        this.bio = null;
    }

    public Roommate(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
