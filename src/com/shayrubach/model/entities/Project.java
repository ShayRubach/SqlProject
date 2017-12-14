package com.shayrubach.model.entities;

import com.shayrubach.model.other.Milestone;

import java.util.ArrayList;
import java.util.Date;

public class Project extends DatabaseEntity {

    private Milestone           milestone = null;
    private String              dateStarted = null;
    private String              description = null;
    private String              name = null;
    private ArrayList<Area>     area = null;
    private String              devStep = null;

    private String   customers;
    private String   devTools;

    public Project(){
        super();
    }

    public Project(Milestone milestone, String dateStarted, String description, String name, ArrayList<Area> area, String customers, String devTools) {
        this.milestone = milestone;
        this.dateStarted = dateStarted;
        this.description = description;
        this.name = name;
        this.area = area;
        this.customers = customers;
        this.devTools = devTools;
    }

    public Project(Milestone milestone, String dateStarted, String description, String name, String devStep) {
        super();
        this.milestone = milestone;
        this.dateStarted = dateStarted;
        this.description = description;
        this.name = name;
        this.devStep = devStep;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public String getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(String dateStarted) {
        this.dateStarted = dateStarted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Area> getArea() {
        return area;
    }

    public void setArea(ArrayList<Area> area) {
        this.area = area;
    }

    public String getCustomers() {
        return customers;
    }

    public void setCustomers(String customers) {
        this.customers = customers;
    }

    public String getDevTools() {
        return devTools;
    }

    public void setDevTools(String devTools) {
        this.devTools = devTools;
    }


    public String getDevStep() {
        return devStep;
    }

    public void setDevStep(String devStep) {
        this.devStep = devStep;
    }
}

