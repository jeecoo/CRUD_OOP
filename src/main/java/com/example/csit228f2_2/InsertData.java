//package com.example.csit228f2_2;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class InsertData {
//    public static void main(String[] args) {
//        try (Connection connection = MySQLConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(
//                     "INSERT INTO user (username, password) VALUES (?, ?)")){
//
//            String username = "jmeconar";
//            String password = "123";
//
//            statement.setString(1, username);
//            statement.setString(2, password);
//
//            int rowsInserted = statement.executeUpdate();
//            if(rowsInserted > 0){
//                System.out.println("Data Inserted Successfully!");
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//}
//
