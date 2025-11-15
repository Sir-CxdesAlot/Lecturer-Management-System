package Controller;

import javafx.fxml.FXML;

public class VerificationPage {
    
    @FXML
    private void handleVerify() {
    NavigationStack.push("/View/VerificationConfirmed.fxml", "Verification Confirmed");
    }
}
