package Controller;
import javafx.fxml.FXML;

public class VerificationConfirmed {

    @FXML
    public void handleBackToLogin() {

    NavigationStack.push("/View/LoginPage.fxml", "Login Page");
    }
    
}
