package com.example.csit228f2_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Label successMessage;

    @FXML
    private Label failureMessage;

    @FXML
    private void register() {
        String username = tfUsername.getText();
        String password = pfPassword.getText();

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO users (username, password) VALUES (?, ?)")){

            statement.setString(1, username);
            statement.setString(2, password);

            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0){
                successMessage.setText("Registration successful!");
                failureMessage.setText("");
            }

        }catch (SQLException e){
            successMessage.setText("");
            failureMessage.setText("Registration failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
