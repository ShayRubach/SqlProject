package com.shayrubach.model.entities;

public class Area extends DatabaseEntity {
    private String name;
    private String specialty;

    public Area(){
        super();
    }

    public Area(String name, String specialty) {
        super();
        setName(name);
        setSpecialty(specialty);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
