package com.example.testpe;

public class Employee {
    private long ID;
    private String name;
    private String date;
    private String gender;

    public Employee(String name, String date, String gender) {
        this.name = name;
        this.date = date;
        this.gender = gender;
    }

    public Employee(long ID, String name, String date, String gender) {
        this.ID = ID;
        this.name = name;
        this.date = date;
        this.gender = gender;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
