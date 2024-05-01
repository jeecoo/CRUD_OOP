//package com.example.csit228f2_2;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class DeleteData {
//    public static void main(String[] args) {
//        try (Connection connection = MySQLConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(
//                     "DELETE FROM user WHERE id = ?")){
//
//            int userIdToDelete = 1;
//
//            statement.setInt(1, userIdToDelete);
//            int rowsDeleted = statement.executeUpdate();
//
//
//
//            if(rowsDeleted > 0){
//                System.out.println("Data Deleted Successfully!");
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//}
