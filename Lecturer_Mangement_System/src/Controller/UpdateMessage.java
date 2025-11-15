package Controller;

import javafx.fxml.FXML;

public class UpdateMessage {

    @FXML
    private void handleReturn() {
        // After updating, remove message and confirmation, then refresh the faculty list
        NavigationStack.pop(); // remove UpdateMessage
        NavigationStack.pop(); // remove UpdateConfirmation

        Object controller = NavigationStack.getCurrentController();
        if (controller instanceof FacultyLecturers) {
            ((FacultyLecturers) controller).loadLecturerCards();
        } else {
            NavigationStack.push("/View/FacultyLecturers.fxml", "Faculty Lecturers");
        }
    }
    
}
