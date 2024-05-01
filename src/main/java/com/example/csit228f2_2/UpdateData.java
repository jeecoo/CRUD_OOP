//package com.example.csit228f2_2;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class UpdateData {
//    public static void main(String[] args) {
//        try (Connection connection = MySQLConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(
//                     "UPDATE user SET name = ?, email = ? WHERE id = ?")){
//
//            String newName = "Russel Palma";
//            String newEmail = "russel_gwapo@cit.edu";
//            int userIdToUpdate = 2;
//
//            statement.setString(1, newName);
//            statement.setString(2, newEmail);
//            statement.setInt(3, userIdToUpdate);
//
//
//
//            int rowsUpdated = statement.executeUpdate();
//
//            if(rowsUpdated > 0){
//                System.out.println("Data Updated Successfully!");
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//}