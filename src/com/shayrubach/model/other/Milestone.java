package com.shayrubach.model.other;

import java.util.Date;
import java.util.Map;

public class Milestone {

    private String name = null;
    private String moneyGranted = null;
    private String dueDate;


    public Milestone(String name, String moneyGranted, String dueDate) {
        this.name = name;
        this.moneyGranted = moneyGranted;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoneyGranted() {
        return moneyGranted;
    }

    public void setMoneyGranted(String moneyGranted) {
        this.moneyGranted = moneyGranted;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
