package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Welcome {

    @FXML
    private Button loginButton;

    @FXML
private void ClickedLogin() {
    NavigationStack.push("/View/LoginPage.fxml", "Login Page");
}


}
