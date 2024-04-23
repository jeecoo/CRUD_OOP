package com.example.csit228f2_2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class ClassController {

    // List to store class schedules
    private List<ClassSchedule> classScheduleList = new ArrayList<>();

    @FXML
    private TextField courseNameField;

    @FXML
    private TextField classTimeField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField instructorField;

    @FXML
    private void addClassSchedule() {
        // Get data from text fields
        String courseName = courseNameField.getText();
        String classTime = classTimeField.getText();
        String location = locationField.getText();
        String instructor = instructorField.getText();

        // Create a new ClassSchedule object
        ClassSchedule newClassSchedule = new ClassSchedule(courseName, classTime, location, instructor);

        // Add the new class schedule to the list
        classScheduleList.add(newClassSchedule);

        // Clear text fields
        clearFields();
    }

    private void clearFields() {
        courseNameField.clear();
        classTimeField.clear();
        locationField.clear();
        instructorField.clear();
    }

};
