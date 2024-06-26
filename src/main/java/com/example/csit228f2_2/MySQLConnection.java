package com.example.csit228f2_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/dbeconar_ezsched";
    public static final String USERNAME = "johnmark";
    public static final String PASSWORD = "123";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void createTableUser() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String createTable =    "CREATE TABLE IF NOT EXISTS users ("
                                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                                    + "username VARCHAR(50) NOT NULL,"
                                    + "password VARCHAR(50) NOT NULL)";

            statement.execute(createTable);

            System.out.println("User Table created successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error creating table", e);
        }
    }

    public static void createTableSchedule() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String createTable = "CREATE TABLE IF NOT EXISTS schedules (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY," +
                                "course_name VARCHAR(100) NOT NULL," +
                                "start_time VARCHAR(50)," +
                                "end_time VARCHAR(50)," +
                                "room_code VARCHAR(20)," +
                                "instructor_name VARCHAR(100)," +
                                "user_id INT," +
                                "FOREIGN KEY (user_id) REFERENCES users(id)" +
                                ");";

            statement.execute(createTable);

            System.out.println("Schedule Table created successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error creating table", e);
        }
    }

    public static void main(String[] args) {
        createTableUser();
        createTableSchedule();
    }
}
