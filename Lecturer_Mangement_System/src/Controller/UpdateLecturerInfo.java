package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import Model.LectureCardModel;
import java.sql.SQLException;

public class UpdateLecturerInfo {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField departmentField;
    @FXML private TextField phoneField;
    @FXML private TextField specialtyField;
    @FXML private TextField assignedCourseField;
    
    private LectureCardModel lecturer;
    private static LectureCardModel tempLecturerData; // Store lecturer data during course selection
    
    public void initialize() {
        // Check if returning from course selection
        Object data = NavigationStack.getData();
        if (data instanceof Model.CourseCardModel) {
            // Course was selected - restore lecturer data and set course
            Model.CourseCardModel selectedCourse = (Model.CourseCardModel) data;
            assignedCourseField.setText(selectedCourse.getCourseTitle());
            NavigationStack.setData(null); // Clear course data
            lecturer = tempLecturerData; // Restore lecturer data
            tempLecturerData = null;
            
            // Re-populate lecturer fields
            if (lecturer != null) {
                firstNameField.setText(lecturer.getFirstName());
                lastNameField.setText(lecturer.getLastName());
                emailField.setText(lecturer.getEmail());
                departmentField.setText(lecturer.getDepartment());
                phoneField.setText(lecturer.getPhone());
                specialtyField.setText(lecturer.getSpecialty());
            }
        } else {
            // Normal initialization - get lecturer data
            lecturer = (LectureCardModel) data;
            if (lecturer != null) {
                // Populate fields with current data
                firstNameField.setText(lecturer.getFirstName());
                lastNameField.setText(lecturer.getLastName());
                emailField.setText(lecturer.getEmail());
                departmentField.setText(lecturer.getDepartment());
                phoneField.setText(lecturer.getPhone());
                specialtyField.setText(lecturer.getSpecialty());
            }
        }
    }

    @FXML
    private void handleUpdate() {
        try {
            // Update the lecturer object with new values
            lecturer.setFirstName(firstNameField.getText().trim());
            lecturer.setLastName(lastNameField.getText().trim());
            lecturer.setEmail(emailField.getText().trim());
            lecturer.setDepartment(departmentField.getText().trim());
            lecturer.setPhone(phoneField.getText().trim());
            lecturer.setSpecialty(specialtyField.getText().trim());
            
            // Validate fields
            if (lecturer.getFirstName().isEmpty() || lecturer.getLastName().isEmpty() 
                || lecturer.getEmail().isEmpty() || lecturer.getDepartment().isEmpty()) {
                showError("Required fields cannot be empty!");
                return;
            }

            // Save to database
            if (lecturer.updateInDatabase()) {
                // Show success confirmation screen
                NavigationStack.push("/View/UpdateConfirmation.fxml", "Update Successful");
            } else {
                showError("Failed to update lecturer information!");
            }
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleBack() {
        NavigationStack.pop();
    }

    @FXML
    private void handleAddCourse() {
        // Store current lecturer data before navigating to course catalog
        tempLecturerData = lecturer;
        NavigationStack.push("/View/CourseCatalog.fxml", "Add Course");
    }
    
    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
