package com.example.csit228f2_2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Schedule {
    private final StringProperty courseName;
    private final StringProperty startTime;
    private final StringProperty endTime;
    private final StringProperty roomNumber;
    private final StringProperty instructorName;

    public Schedule(String courseName, String startTime, String endTime, String roomNumber, String instructorName) {
        this.courseName = new SimpleStringProperty(courseName);
        this.startTime = new SimpleStringProperty(startTime);
        this.endTime = new SimpleStringProperty(endTime);
        this.roomNumber = new SimpleStringProperty(roomNumber);
        this.instructorName = new SimpleStringProperty(instructorName);
    }

    public StringProperty courseNameProperty() {
        return courseName;
    }

    public StringProperty startTimeProperty() {
        return startTime;
    }

    public StringProperty endTimeProperty() {
        return endTime;
    }

    public StringProperty roomNumberProperty() {
        return roomNumber;
    }

    public StringProperty instructorNameProperty() {
        return instructorName;
    }


}
