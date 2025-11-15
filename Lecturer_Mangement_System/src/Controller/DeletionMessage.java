package Controller;

import javafx.fxml.FXML;

public class DeletionMessage {
    
    @FXML
    private void handleReturnHome() {
        // After deletion, go back to the faculty list and refresh it
        NavigationStack.pop(); // remove DeletionMessage
        NavigationStack.pop(); // remove DeletionConfirmation

        // Now the FacultyLecturers screen should be on top; get its controller and refresh
        Object controller = NavigationStack.getCurrentController();
        if (controller instanceof FacultyLecturers) {
            ((FacultyLecturers) controller).loadLecturerCards();
        } else {
            // If not found, navigate to FacultyLecturers to be safe
            NavigationStack.push("/View/FacultyLecturers.fxml", "Faculty Lecturers");
        }
    }
}
