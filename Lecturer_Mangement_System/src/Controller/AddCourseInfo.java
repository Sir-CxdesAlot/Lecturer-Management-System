package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddCourseInfo {

    @FXML
    private void handleAddCourse(ActionEvent event) {
        NavigationStack.push("/View/CourseCatalog.fxml", "Course Catalog");
    }

    @FXML
    private void handleBack() {
        NavigationStack.pop();
    }
}
