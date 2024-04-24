package com.example.csit228f2_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDeleteUserController {
    public Button deleteButton;

    @FXML
    public Label updateUserMessageLabel;
    @FXML
    public TextField useridTextField;
    public Button backButton;

    @FXML
    private void saveUserData() {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM users WHERE id = ?")) {

            // Check if the user ID text field is not empty and can be parsed into an integer
            if (!useridTextField.getText().isEmpty()) {
                try {
                    int userIdToUpdate = Integer.parseInt(useridTextField.getText());

                    statement.setInt(1, userIdToUpdate);

                    int rowsUpdated = statement.executeUpdate();

                    if (rowsUpdated > 0) {
                        updateUserMessageLabel.setText("Data Deleted Successfully!");
                    } else {
                        updateUserMessageLabel.setText("Failed to delete data! User ID not found.");
                    }
                } catch (NumberFormatException e) {
                    updateUserMessageLabel.setText("Please enter a valid ID!");
                }
            } else {
                updateUserMessageLabel.setText("Please enter a user ID!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void backButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Admin Panel");
        stage.show();
    }
}
