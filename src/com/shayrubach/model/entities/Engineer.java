package com.shayrubach.model.entities;

import java.util.ArrayList;
import java.util.Map;

public class Engineer extends DatabaseEntity {

    private String firstName = null;
    private String lastName = null;
    private String address = null;
    private String phoneNumber = null;
    private String birthDate = null;
    private String area = null;

    public Engineer(){
        super();
    }

    public Engineer(String firstName, String lastName, String address, String phoneNumber, String birthDate, String area) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Engineer)) return false;

        Engineer engineer = (Engineer) o;

        return this.id != null ? this.id.equals(engineer.getId()) : engineer.getId() == null;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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


}
