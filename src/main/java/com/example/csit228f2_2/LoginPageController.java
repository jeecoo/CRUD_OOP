package com.example.csit228f2_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {

    public Label Register;
    public Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    public void loginButtonOnAction(ActionEvent event) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {

            statement.setString(1, usernameTextField.getText());
            statement.setString(2, passwordPasswordField.getText());

            ResultSet resultSet = statement.executeQuery();

            if (!usernameTextField.getText().isEmpty() && !passwordPasswordField.getText().isEmpty()) {
                loginMessageLabel.setText("Logging in!");
                if (resultSet.next()) {
                    String  username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    if (username.equals("admin") && password.equals("admin")) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
                        Scene scene = new Scene(loader.load());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Admin Page");
                        stage.show();
                    } else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPage.fxml"));
                        Scene scene = new Scene(loader.load());
                        UserPageController userPageController = loader.getController();
                        userPageController.setUsername(username);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("User Page");
                        stage.show();
                    }
                } else {
                    loginMessageLabel.setText("Invalid username/password");
                }
            } else {
                loginMessageLabel.setText("Please enter your username and password!");
            }

            resultSet.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void openRegistration(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterPage.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
    }


}
