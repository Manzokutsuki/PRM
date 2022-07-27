package com.example.demoactivityintent;

import java.io.Serializable;

public class Student implements Serializable {
    public String Name;
    public int YearOfBirth;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getYearOfBirth() {
        return YearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        YearOfBirth = yearOfBirth;
    }

    public Student(String name, int yearOfBirth) {
        Name = name;
        YearOfBirth = yearOfBirth;
    }


}
