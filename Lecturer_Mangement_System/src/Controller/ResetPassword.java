package Controller;

import javafx.fxml.FXML;

public class ResetPassword {

    @FXML
    private void handleReset() {
    NavigationStack.push("/View/VerificationPage.fxml", "Verification Page");
    }

    @FXML
    private void handleBackToLogin() {
        NavigationStack.pop();
    }
    
}
