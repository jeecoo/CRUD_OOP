package com.example.csit228f2_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MySQLConnection.createTableUser();
        MySQLConnection.createTableSchedule();
        // Load the login screen
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Parent loginRoot = loginLoader.load();
        Scene loginScene = new Scene(loginRoot);

        // Load the register screen
        FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent registerRoot = registerLoader.load();
        Scene registerScene = new Scene(registerRoot);

//        loginScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
  //     registerScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
