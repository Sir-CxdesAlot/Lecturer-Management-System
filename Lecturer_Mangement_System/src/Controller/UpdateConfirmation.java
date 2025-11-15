package Controller;

import javafx.fxml.FXML;

public class UpdateConfirmation {

    @FXML
    private void handleConfirmUpdate() {
    NavigationStack.push("/View/UpdateMessage.fxml", "Update Message");
    }
    
}
