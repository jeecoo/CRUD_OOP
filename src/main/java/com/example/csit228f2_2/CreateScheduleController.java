package com.example.csit228f2_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateScheduleController {

    public Button createButton;
    public Button backButton;
    private final List<Schedule> schedules;

    @FXML
    public Label userLabel;
    public TextField courseNameTextField;
    public TextField startTimeTextField;
    public Label createMessageLabel;
    public TextField endTimeTextField;
    public TextField roomNumberTextField;
    public TextField instructorNameTextField;

    public CreateScheduleController() {
        schedules = new ArrayList<>();
    }

    public void setUsername(String username) {
        userLabel.setText(username.toUpperCase());
    }

    @FXML
    public void createButtonOnAction(ActionEvent event) {
        String courseName = courseNameTextField.getText();
        String startTime = startTimeTextField.getText();
        String endTime = endTimeTextField.getText();
        String roomNumber = roomNumberTextField.getText();
        String instructorName = instructorNameTextField.getText();
        String username = userLabel.getText();

        int userId = getUserIdByUsername(username);

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO schedules (course_name, start_time, end_time, room_code, instructor_name, user_id) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, courseName);
            statement.setString(2, startTime);
            statement.setString(3, endTime);
            statement.setString(4, roomNumber);
            statement.setString(5, instructorName);
            statement.setInt(6, userId); // Set the user ID

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                createMessageLabel.setText("Schedule created successfully");
            } else {
                createMessageLabel.setText("Failed to create schedule");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            createMessageLabel.setText("Database error");
        }

        Schedule schedule = new Schedule(courseName, startTime, endTime, roomNumber, instructorName);
        schedules.add(schedule);
        createMessageLabel.setText("Schedule created successfully");
    }

    private int getUserIdByUsername(String username) {
        int userId = -1;
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id FROM users WHERE username = ?")) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    @FXML
    public void backButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPage.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Admin Panel");
        stage.show();
    }
}
