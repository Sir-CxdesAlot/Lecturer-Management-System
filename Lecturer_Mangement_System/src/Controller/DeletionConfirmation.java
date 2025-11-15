package Controller;

import Data.LecturerDAO;
import Model.LectureCardModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DeletionConfirmation {

    @FXML
    private void handleDeletionConfirmation() {
        // Retrieve the lecturer that was set by the card controller
        Object data = NavigationStack.getData();
        if (data instanceof LectureCardModel) {
            LectureCardModel card = (LectureCardModel) data;
            try {
                LecturerDAO dao = new LecturerDAO();
                dao.deleteLecturer(card.getLecturerId());

                // Clear the shared data
                NavigationStack.setData(null);

                // Show confirmation message screen
                NavigationStack.push("/View/DeletionMessage.fxml", "Deletion Message");
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Deletion Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to delete lecturer: " + e.getMessage());
                alert.showAndWait();
            }
        } else {
            // No lecturer selected - show error
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Deletion Error");
            alert.setHeaderText(null);
            alert.setContentText("No lecturer selected for deletion.");
            alert.showAndWait();
        }
    }
    
}
