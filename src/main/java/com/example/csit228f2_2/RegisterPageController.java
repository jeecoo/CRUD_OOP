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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterPageController {
    public Label Login;
    public Label registerMessageLabel;
    public Button registerButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    public void registerButtonOnAction(ActionEvent event) {
        try (Connection connection = MySQLConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users (username, password) VALUES (?, ?)")) {

            statement.setString(1, usernameTextField.getText());
            statement.setString(2, passwordPasswordField.getText());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                registerMessageLabel.setText("Registration successful!");
            } else {
                registerMessageLabel.setText("Registration failed!");
            }
        } catch (SQLException e) {
            registerMessageLabel.setText("Registration failed!");
            e.printStackTrace();
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void openLogin(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
}
