package com.shayrubach.model.entities;

import java.util.ArrayList;
import java.util.Map;

public class Engineer extends DatabaseEntity {

    private String firstName = null;
    private String lastName = null;
    private String address = null;
    private String phoneNumber = null;
    private String age = null;
    private ArrayList<Map.Entry<String,Integer>> projects = null;          //pair values of <Project name , Rate (1-10)>

    public Engineer(){
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Engineer)) return false;

        Engineer engineer = (Engineer) o;

        return this.id != null ? this.id.equals(engineer.getId()) : engineer.getId() == null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public ArrayList<Map.Entry<String, Integer>> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Map.Entry<String, Integer>> projects) {
        this.projects = projects;
    }
}
