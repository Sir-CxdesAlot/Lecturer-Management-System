package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import Data.LecturerDAO;
import java.sql.SQLException;

public class AddLecturerInfo {
    @FXML private TextField staffNumberField;
    @FXML private TextField officeNumberField;
    @FXML private TextField staffNameField;
    @FXML private TextField buildingLocationField;
    @FXML private TextField phoneField;
    @FXML private TextField specialtyField;
    @FXML private TextField assignedCourseField;

    @FXML
    private void handleSubmit(ActionEvent event) {
        // Validate required fields
        if (staffNumberField.getText().trim().isEmpty() || 
            staffNameField.getText().trim().isEmpty() || 
            officeNumberField.getText().trim().isEmpty() || 
            buildingLocationField.getText().trim().isEmpty()) {
            showError("Staff Number, Staff Name, Office Number, and Building Location are required!");
            return;
        }

        try {
            String[] names = staffNameField.getText().trim().split(" ", 2);
            String firstName = names[0];
            String lastName = names.length > 1 ? names[1] : "";

            // Save to database
            LecturerDAO lecturerDAO = new LecturerDAO();
            lecturerDAO.addLecturer(
                firstName,
                lastName,
                staffNumberField.getText().trim(),
                buildingLocationField.getText().trim(),
                phoneField.getText().trim(),
                specialtyField.getText().trim()
            );

            // Show success message
            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Lecturer added successfully!");
            successAlert.showAndWait();

            // Return to faculty screen and refresh
            NavigationStack.pop();
            
            // Get the FacultyLecturers controller and refresh the cards
            Object currentController = NavigationStack.getCurrentController();
            if (currentController instanceof FacultyLecturers) {
                ((FacultyLecturers) currentController).loadLecturerCards();
            }
        } catch (SQLException e) {
            showError("Failed to add lecturer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddCourse(ActionEvent event) {
        NavigationStack.push("/View/CourseCatalog.fxml", "Add Course");
    }

    @FXML
    private void handleBack() {
        NavigationStack.pop();
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}