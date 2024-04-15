package com.example.csit228f2_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteController {

    @FXML
    private TextField tfUserID;

    @FXML
    private TextField tfNewUsername;

    @FXML
    private PasswordField pfNewPassword;

    @FXML
    private Label successMessage;

    @FXML
    private Label failureMessage;

    @FXML
    private void saveUserData() {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM user WHERE id = ?")){

            int userIdToUpdate = Integer.parseInt(tfUserID.getText());

            statement.setInt(1, userIdToUpdate);
            int rowsUpdated = statement.executeUpdate();

            if(rowsUpdated > 0){
                successMessage.setText("Data Deleted Successfully!");
                failureMessage.setText("");
            } else {
                successMessage.setText("");
                failureMessage.setText("Failed to update data!");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
