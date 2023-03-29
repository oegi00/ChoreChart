package com.example.chorechart.data;

import java.io.Serializable;

public class Chore implements Serializable {

    private String choreName;
    private String assignee;
    private String location;
    private String deadline;
    private String description;

    public Chore() {
        this.choreName = null;
        this.assignee = null;
        this.location = null;
        this.deadline = null;
        this.description = null;
    }
    public Chore(String choreName, String assignee, String location, String deadline, String description){
        this.choreName = choreName;
        this.assignee = assignee;
        this.location = location;
        this.deadline = deadline;
        this.description = description;
    }

    public String getChoreName() {
        return choreName;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getLocation() {
        return location;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setChoreName(String choreName) {
        this.choreName = choreName;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean compareChores(Chore chore) {
        if (choreName.equals(chore.choreName)) {
            if (assignee.equals(chore.assignee)) {
                if (location.equals(chore.location)) {
                    if (deadline.equals(chore.deadline)) {
                        if (description.equals(chore.description)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
