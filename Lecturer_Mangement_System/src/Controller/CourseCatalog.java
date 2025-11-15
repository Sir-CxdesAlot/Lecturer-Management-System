package Controller;
import Data.CourseCatalog2Data;
import Model.CourseCardModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * CourseCatalog - Controller for the course listing screen
 *
 * This screen shows all courses in a scrollable list and lets users:
 * - View existing courses
 * - Add new courses
 * - Log out
 *
 * Navigation examples:
 * - "Add New Course" button pushes AddCourseInfo screen
 * - "Logout" returns to login (pops back to root)
 *
 * Resource path: /View/CourseCatalog.fxml
 */
public class CourseCatalog implements Initializable {

    @FXML
    private ScrollPane CourseScrollP;

    private VBox cardContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create a VBox container for vertical layout
        cardContainer = new VBox();
        cardContainer.setSpacing(15); // Space between cards
        cardContainer.setStyle("-fx-padding: 10;"); // Add some padding around the container
        
        // Set the VBox as the content of the ScrollPane
        CourseScrollP.setContent(cardContainer);
        
        // Load all course cards
        loadCourseCards();
    }

    private void loadCourseCards() {
        try {
            // Loop through all the dummy data and create cards
            for (int i = 0; i < CourseCatalog2Data.CourseTitle.length; i++) {
                // Create a new course model with data
                CourseCardModel course = new CourseCardModel(
                    CourseCatalog2Data.CourseTitle[i],
                    CourseCatalog2Data.LecturerTitle[i],
                    CourseCatalog2Data.CourseInfo[i]
                );

                // Load the FXML for individual card
                FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/View/CourseCatalog2.fxml"));
                
                // Load the card view
                javafx.scene.Node cardView = cardLoader.load();
                
                // Get the controller and set the data
                CourseCatalog2 cardController = cardLoader.getController();
                cardController.setCourse(course);
                
                // Add the card to the VBox container (this makes them stack vertically)
                cardContainer.getChildren().add(cardView);
            }
            
            System.out.println("Successfully loaded " + CourseCatalog2Data.CourseTitle.length + " course cards");
            
        } catch (IOException e) {
            System.err.println("Error loading course cards: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to refresh/reload cards (optional)
    public void refreshCards() {
        cardContainer.getChildren().clear();
        loadCourseCards();
    }

    @FXML
    private void handleBack() {
        NavigationStack.pop();
    }

    @FXML
    private void handleLogout() {
        // Return to login screen
        NavigationStack.push("/View/LoginPage.fxml", "Login");
    }

    @FXML
    private void handleAddNewCourse() {
        NavigationStack.push("/View/AddCourseInfo.fxml", "Add New Course");
    }

    @FXML
    private Button addCourseButton;
}