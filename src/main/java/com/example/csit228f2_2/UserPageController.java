package com.example.csit228f2_2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserPageController {
    public Label userLabel;

    public void setUsername(String username) {
        userLabel.setText(username.toUpperCase());
    }


    @FXML
    private TableView<Schedule> scheduleTableView;
    @FXML
    private TableColumn<Schedule, String> courseNameColumn;
    @FXML
    private TableColumn<Schedule, String> startTimeColumn;
    @FXML
    private TableColumn<Schedule, String> endTimeColumn;
    @FXML
    private TableColumn<Schedule, String> roomNumberColumn;
    @FXML
    private TableColumn<Schedule, String> instructorNameColumn;


    public void initialize() {
        String username = userLabel.getText();
        setUsername(username);
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        instructorNameColumn.setCellValueFactory(new PropertyValueFactory<>("instructorName"));

        populateTableView();
    }

//    private void populateTableView() {
//        String username = userLabel.getText();
//        ObservableList<Schedule> schedules = FXCollections.observableArrayList(fetchDataFromDatabase(username));
//        scheduleTableView.setItems(schedules);
//    }

    private void populateTableView() {

        List<Schedule> schedules = fetchDataFromDatabase();

        scheduleTableView.getItems().clear();
        scheduleTableView.getItems().addAll(schedules);
    }


//    private List<Schedule> fetchDataFromDatabase(String username) {
//        List<Schedule> schedules = new ArrayList<>();
//        int userId = getUserIdByUsername(username);
//        if (userId == -1) {
//            System.err.println("User not found: " + username);
//            return schedules;
//        }
//
//        try (Connection connection = MySQLConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(
//                     "SELECT * FROM schedules WHERE user_id = ?")) {
//            statement.setInt(1, userId);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                Schedule schedule = new Schedule(
//                        resultSet.getString("course_name"),
//                        resultSet.getString("start_time"),
//                        resultSet.getString("end_time"),
//                        resultSet.getString("room_code"),
//                        resultSet.getString("instructor_name"));
//                schedules.add(schedule);
//            }
//        } catch (SQLException e) {
//            System.err.println("Error fetching schedules from the database: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return schedules;
//    }

    private List<Schedule> fetchDataFromDatabase() {
        List<Schedule> schedules = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM schedules");
            while (resultSet.next()) {
                Schedule schedule = new Schedule(
                        resultSet.getString("course_name"),
                        resultSet.getString("start_time"),
                        resultSet.getString("end_time"),
                        resultSet.getString("room_code"),
                        resultSet.getString("instructor_name"));
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }


    private int getUserIdByUsername(String username) {
        int userId = -1;
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id FROM users WHERE username = ?")) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
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

    @FXML
    public void openCreateSchedule(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateSchedule.fxml"));
            Parent deleteRoot = loader.load();
            Scene deleteScene = new Scene(deleteRoot);
            CreateScheduleController controller = loader.getController();
            controller.setUsername(userLabel.getText());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(deleteScene);
            stage.setTitle("Create Schedule");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading CreateSchedule.fxml: " + e.getMessage());
        }
    }

//    @FXML
//    public void openEditSchedule(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminUpdateUser.fxml"));
//        Parent updateRoot = loader.load();
//        Scene updateScene = new Scene(updateRoot);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(updateScene);
//        stage.setTitle("Update User");
//        stage.show();
//    }
}
