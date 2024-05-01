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
import java.sql.SQLException;

public class DeleteScheduleController {
    public Label userLabel;
    public TextField courseNameTextField;
    public Button backButton;
    public Label deleteMessageLabel;
    public Button deleteButton;


    @FXML
    private void deleteButtonOnAction(ActionEvent event) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM schedules WHERE course_name = ?")) {

            if (!courseNameTextField.getText().isEmpty()) {
                try {
                    String courseName = courseNameTextField.getText();

                    statement.setString(1, courseName);

                    int rowsUpdated = statement.executeUpdate();

                    if (rowsUpdated > 0) {
                        deleteMessageLabel.setText("Data Deleted Successfully!");
                    } else {
                        deleteMessageLabel.setText("Failed to delete data! Course name not found");
                    }
                } catch (NumberFormatException e) {
                    deleteMessageLabel.setText("Please enter a valid course name.");
                }
            } else {
                deleteMessageLabel.setText("Please enter a course name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void setUsername(String username) {
        userLabel.setText(username.toUpperCase());
    }
}
