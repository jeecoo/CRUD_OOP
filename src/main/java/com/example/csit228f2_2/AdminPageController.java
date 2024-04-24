package com.example.csit228f2_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPageController {

    @FXML
    public void openUpdateScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminUpdateUser.fxml"));
        Parent updateRoot = loader.load();
        Scene updateScene = new Scene(updateRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(updateScene);
        stage.setTitle("Update User");
        stage.show();
    }

    @FXML
    public void openDeleteScreen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDeleteUser.fxml"));
            Parent deleteRoot = loader.load();
            Scene deleteScene = new Scene(deleteRoot);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(deleteScene);
            stage.setTitle("Delete User");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading AdminDeleteUser.fxml: " + e.getMessage());
        }
    }

    @FXML
    private void openLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
}
