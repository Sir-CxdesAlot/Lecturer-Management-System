package Controller;

import Data.LecturerDAO;
import Model.Lecturer;
import Model.LectureCardModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class FacultyLecturers implements Initializable {

    @FXML
    private ScrollPane LecturerScrollP;

    private VBox cardContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create a VBox container for vertical layout
        cardContainer = new VBox();
        cardContainer.setSpacing(15); // Space between cards
        cardContainer.setStyle("-fx-padding: 10;"); // Add some padding around the container

        // Set the VBox as the content of the ScrollPane
        LecturerScrollP.setContent(cardContainer);

        // Load all lecturer cards
        loadLecturerCards();
    }

    public void loadLecturerCards() {
        try {
            // Clear existing cards
            cardContainer.getChildren().clear();

            // Get lecturers from database
            LecturerDAO lecturerDAO = new LecturerDAO();

            List<Lecturer> lecturers = lecturerDAO.getAllLecturers();

            // Create cards for each lecturer if any exist
            if (!lecturers.isEmpty()) {
                 
                for (Lecturer lecturer : lecturers) {
                    // Convert Lecturer to LecturerCardModel
                    LectureCardModel cardModel = lecturer.toCardModel();
                    
                    // Load the FXML for individual card
                    FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/View/FacultyLecturers2.fxml"));

                    // Load the card view
                    javafx.scene.Node cardView = cardLoader.load();

                    // Get the controller and set the data
                    FacultyLecturers2 cardController = cardLoader.getController();
                    cardController.setLecturer(cardModel);

                    // Add the card to the VBox container
                    cardContainer.getChildren().add(cardView);
                }

                System.out.println("Successfully loaded " + lecturers.size() + " lecturer cards");
            }
        } catch (SQLException e) {
            showError("Database Error", "Failed to load lecturers: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            showError("Layout Error", "Failed to create lecturer cards: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showMessage(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Button handler for "Add New Lecturer"
    @FXML
    private void handleAddLecturer(ActionEvent event) {
        // CALL navigateTo with the required title argument
        NavigationStack.push("/View/AddLecturerInfo.fxml", "Add Lecturer Info");
    }

    @FXML
    private void handleLogout(ActionEvent event) {

        NavigationStack.push("/View/LoginPage.fxml", "Login");
    }
}
