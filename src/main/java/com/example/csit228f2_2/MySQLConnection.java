package com.example.csit228f2_2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class MySQLConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/dbeconar";
    public static final String USERNAME = "johnmark";
    public static final String PASSWORD = "123";
    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void main(String[] args) {
        getConnection();
    }
}
