package com.shayrubach.model.other;

import java.util.Date;
import java.util.Map;

public class Milestone {
    public final static String[] MILESTONE_LIST = {"Design","Development","Maintenance"};
    public Map.Entry<String,Double> milestone = null;
    public Date dueDate;

    public Milestone(Map.Entry<String, Double> milestone, Date dueDate) {
        //TODO: check if ms date exceeds due date?
        this.milestone = milestone;
        this.dueDate = dueDate;
    }


    @Override
    public String toString() {
        return      "Milestone: "       + milestone.getKey()
                + ", Money granted: "   + milestone.getValue().toString()
                + ", Due date: "        + dueDate.toString();
    }

}
