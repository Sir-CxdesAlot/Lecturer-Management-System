package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddLecturerConfirmation {

    @FXML
    private void handleBackToFaculty(ActionEvent event) {
        NavigationStack.pop();
    }
    
    @FXML
    private void handleAddAnotherLecturer() {
        NavigationStack.push("/View/AddLecturerInfo.fxml", "Add Lecturer");
    }

}
