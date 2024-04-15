package com.example.csit228f2_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Label actionTarget;

    @FXML
    private void login(ActionEvent event) {
        String username = tfUsername.getText();
        String password = pfPassword.getText();

        try (Connection connection = MySQLConnection.getConnection();
             Statement statement = connection.createStatement()){

            String selectQuery = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            if (resultSet.next()) {
                if (username.equals("admin") && password.equals("admin")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Admin Panel");
                    stage.show();
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Welcome");
                    stage.show();
                }
            } else {
                actionTarget.setText("Invalid username/password");
            }
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }



    @FXML
    private void openRegistration(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
    }
}
