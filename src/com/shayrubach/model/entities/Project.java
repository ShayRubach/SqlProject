package com.shayrubach.model.entities;

import com.shayrubach.model.other.Milestone;

import java.util.ArrayList;
import java.util.Date;

public class Project extends DatabaseEntity {

    private Milestone milestone = null;
    private Date                dateStarted = null;
    private String              description = null;
    private String              name = null;
    private ArrayList<Area>     area = null;
    private ArrayList<String>   customers = null;
    private ArrayList<String>   devTools = null;

    public Project(){
        super();
    }

    public Project(Milestone milestone, Date dateStarted, String description, String name, ArrayList<Area> area, ArrayList<String> customers, ArrayList<String> devTools) {
        this.milestone = milestone;
        this.dateStarted = dateStarted;
        this.description = description;
        this.name = name;
        this.area = area;
        this.customers = customers;
        this.devTools = devTools;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
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

    public ArrayList<String> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<String> customers) {
        this.customers = customers;
    }

    public ArrayList<String> getDevTools() {
        return devTools;
    }

    public void setDevTools(ArrayList<String> devTools) {
        this.devTools = devTools;
    }
}
