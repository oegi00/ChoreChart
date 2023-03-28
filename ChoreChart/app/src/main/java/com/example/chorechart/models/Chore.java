package com.example.chorechart.models;

import java.util.Date;

public class Chore {

    String name;
    String location;
    String assignee;
    Date deadline;
    String description;

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getDescription() {
        return description;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }
}
