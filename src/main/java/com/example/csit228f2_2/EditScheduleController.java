package com.example.csit228f2_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
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

public class EditScheduleController {

    public Button updateButton;
    public Button backButton;
    @FXML
    private Label userLabel;
    @FXML
    private TextField schedIdTextField;
    @FXML
    private TextField courseNameTextField;
    @FXML
    private TextField startTimeTextField;
    @FXML
    private TextField endTimeTextField;
    @FXML
    private TextField roomNumberTextField;
    @FXML
    private TextField instructorNameTextField;
    @FXML
    private Label createMessageLabel;

    public void setUsername(String username) {
        userLabel.setText(username.toUpperCase());
    }

    public void initialize() {
        String schedIdText = schedIdTextField.getText();
        if (!schedIdText.isEmpty()) {
            int scheduleId = Integer.parseInt(schedIdText);
            loadSchedule(scheduleId);
        }
    }

    public void loadSchedule(int scheduleId) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM schedules WHERE id = ?")) {
            statement.setInt(1, scheduleId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                schedIdTextField.setText(String.valueOf(resultSet.getInt("id")));
                courseNameTextField.setText(resultSet.getString("course_name"));
                startTimeTextField.setText(resultSet.getString("start_time"));
                endTimeTextField.setText(resultSet.getString("end_time"));
                roomNumberTextField.setText(resultSet.getString("room_code"));
                instructorNameTextField.setText(resultSet.getString("instructor_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            createMessageLabel.setText("Error loading schedule details");
        }
    }

    @FXML
    public void updateButtonOnAction(ActionEvent event) {
        String schedIdText = schedIdTextField.getText().trim();
        if (schedIdText.isEmpty()) {
            createMessageLabel.setText("Please enter schedule ID");
            return;
        }

        int scheduleId;
        try {
            scheduleId = Integer.parseInt(schedIdText);
        } catch (NumberFormatException e) {
            createMessageLabel.setText("Invalid schedule ID");
            return;
        }

        String courseName = courseNameTextField.getText();
        String startTime = startTimeTextField.getText();
        String endTime = endTimeTextField.getText();
        String roomNumber = roomNumberTextField.getText();
        String instructorName = instructorNameTextField.getText();

        // Check if the schedule ID exists in the database
        if (!isValidScheduleId(scheduleId)) {
            createMessageLabel.setText("Invalid schedule ID");
            return;
        }

        try (Connection connection = MySQLConnection.getConnection()) {
            StringBuilder queryBuilder = new StringBuilder("UPDATE schedules SET");
            if (!courseName.isEmpty()) {
                queryBuilder.append(" course_name=?,");
            }
            if (!startTime.isEmpty()) {
                queryBuilder.append(" start_time=?,");
            }
            if (!endTime.isEmpty()) {
                queryBuilder.append(" end_time=?,");
            }
            if (!roomNumber.isEmpty()) {
                queryBuilder.append(" room_code=?,");
            }
            if (!instructorName.isEmpty()) {
                queryBuilder.append(" instructor_name=?,");
            }
            queryBuilder.deleteCharAt(queryBuilder.length() - 1);
            queryBuilder.append(" WHERE id=?");

            try (PreparedStatement statement = connection.prepareStatement(queryBuilder.toString())) {
                int parameterIndex = 1;
                if (!courseName.isEmpty()) {
                    statement.setString(parameterIndex++, courseName);
                }
                if (!startTime.isEmpty()) {
                    statement.setString(parameterIndex++, startTime);
                }
                if (!endTime.isEmpty()) {
                    statement.setString(parameterIndex++, endTime);
                }
                if (!roomNumber.isEmpty()) {
                    statement.setString(parameterIndex++, roomNumber);
                }
                if (!instructorName.isEmpty()) {
                    statement.setString(parameterIndex++, instructorName);
                }
                statement.setInt(parameterIndex, scheduleId);

                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    createMessageLabel.setText("Schedule updated successfully!");
                } else {
                    createMessageLabel.setText("Failed to update schedule");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            createMessageLabel.setText("Database error");
        }
    }

    private boolean isValidScheduleId(int scheduleId) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id FROM schedules WHERE id = ?")) {
            statement.setInt(1, scheduleId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    public void backButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPage.fxml"));
        Parent root = loader.load();
        UserPageController controller = loader.getController();
        controller.setUsername(userLabel.getText());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("User Page");
        stage.show();
    }
}
