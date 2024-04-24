    package com.example.csit228f2_2;

    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Node;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.control.PasswordField;
    import javafx.scene.control.TextField;
    import javafx.stage.Stage;

    import java.io.IOException;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;

    public class AdminUpdateUserController {
        @FXML
        public Label updateUserMessageLabel;
        @FXML
        public TextField useridTextField;
        @FXML
        public Button saveButton;
        @FXML
        public PasswordField newPasswordPasswordField;
        @FXML
        public TextField newUsernameTextField1;
        public Button backButton;

        @FXML
        private void saveUserData(ActionEvent event) {
            try (Connection connection = MySQLConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "UPDATE users SET username = ?, password = ? WHERE id = ?")) {

                String newName = newUsernameTextField1.getText();
                String newPassword = newPasswordPasswordField.getText();
                int userIdToUpdate;

                if (!newUsernameTextField1.getText().isEmpty() && !newPasswordPasswordField.getText().isEmpty() && !useridTextField.getText().isEmpty()) {
                    if (!useridTextField.getText().isEmpty() && useridTextField.getText().matches("\\d+")) {

                        userIdToUpdate = Integer.parseInt(useridTextField.getText());
                        statement.setString(1, newName);
                        statement.setString(2, newPassword);
                        statement.setInt(3, userIdToUpdate);

                        int rowsUpdated = statement.executeUpdate();

                        if (!newName.isEmpty() && !newPassword.isEmpty()) {
                            if (rowsUpdated > 0) {
                                updateUserMessageLabel.setText("Data Updated Successfully!");
                            } else {
                                updateUserMessageLabel.setText("Failed to update data! User ID not found.");
                            }
                        } else {
                            updateUserMessageLabel.setText("Please enter username, password!");
                        }
                    } else {
                        updateUserMessageLabel.setText("Please enter a valid user ID!");
                    }
                }else{
                    updateUserMessageLabel.setText("Please enter user ID, username, and password!");
                }
            } catch (SQLException e) {
                updateUserMessageLabel.setText("Error occurred while updating data!");
                e.printStackTrace();
            }

        }

        @FXML
        public void backButtonOnAction(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Admin Panel");
            stage.show();
        }
    }
