package org.example.javaprojectpractise1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public  class Subject {
    // In your controller class
    private String name;
    private  double obtainedMarks;
    private  double totalMarks;
    private  int creditHours;
    private  double gpa;


    public Subject(String name, double gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    public Subject(String name, double obtainedMarks, double totalMarks, int creditHours , double gpa) {
        this.name = name;
        this.obtainedMarks = obtainedMarks;
        this.totalMarks = totalMarks;
        this.creditHours = creditHours;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(double obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}