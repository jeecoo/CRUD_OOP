package com.example.csit228f2_2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClassSchedule {
    private StringProperty courseName;
    private StringProperty classTime;
    private StringProperty location;
    private StringProperty instructor;

    // Constructor
    public ClassSchedule(String courseName, String classTime, String location, String instructor) {
        this.courseName = new SimpleStringProperty(courseName);
        this.classTime = new SimpleStringProperty(classTime);
        this.location = new SimpleStringProperty(location);
        this.instructor = new SimpleStringProperty(instructor);
    }

    // Getter and setter methods for courseName
    public StringProperty courseNameProperty() {
        return courseName;
    }

    public String getCourseName() {
        return courseName.get();
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    // Getter and setter methods for classTime
    public StringProperty classTimeProperty() {
        return classTime;
    }

    public String getClassTime() {
        return classTime.get();
    }

    public void setClassTime(String classTime) {
        this.classTime.set(classTime);
    }

    // Getter and setter methods for location
    public StringProperty locationProperty() {
        return location;
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    // Getter and setter methods for instructor
    public StringProperty instructorProperty() {
        return instructor;
    }

    public String getInstructor() {
        return instructor.get();
    }

    public void setInstructor(String instructor) {
        this.instructor.set(instructor);
    }
}
