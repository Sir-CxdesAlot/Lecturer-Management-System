package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import Data.AdminDAO;
import java.sql.SQLException;

public class LoginPage {
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;

    @FXML
    private void handleLogin() {
        String userInput = username.getText().trim();
        String passInput = password.getText();

        if (userInput.isEmpty() || passInput.isEmpty()) {
            showError("Username and password cannot be empty!");
            return;
        }

        try {
            AdminDAO adminDAO = new AdminDAO();
            boolean isValid = adminDAO.checkLogin(userInput, passInput);
            
            if (isValid) {
                NavigationStack.push("/View/FacultyLecturers.fxml", "Faculty Lecturers");
            } else {
                showError("Invalid username or password!");
                password.clear(); // Clear password field for security
            }
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleForgotPassword(ActionEvent event) {
        NavigationStack.push("/View/ResetPassword.fxml", "Reset Password");
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
