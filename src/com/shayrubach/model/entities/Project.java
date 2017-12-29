package com.shayrubach.model.entities;

import com.shayrubach.model.other.Milestone;

import java.util.ArrayList;
import java.util.Date;

public class Project extends DatabaseEntity {

    private String              dateStarted = null;
    private String              description = null;
    private String              name = null;
    private Area                area = null;
    private String              devStep = null;

    private String   customers;
    private String   devTools;

    public Project(){
        super();
    }

    public Project(String dateStarted, String description, String name, Area area, String customers, String devTools) {
        this.dateStarted = dateStarted;
        this.description = description;
        this.name = name;
        this.area = area;
        this.customers = customers;
        this.devTools = devTools;
    }

    public Project(String dateStarted, String description, String name, String devStep) {
        super();
        this.dateStarted = dateStarted;
        this.description = description;
        this.name = name;
        this.devStep = devStep;
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
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

