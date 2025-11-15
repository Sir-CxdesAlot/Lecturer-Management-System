package Controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Util.DatabaseSetup;
import Util.DatabaseConnection;

/**
 * Main application entry point for Lecturer Management System
 * VM Arguments: --module-path C:/Java/javafx-sdk-21.0.8/lib --add-modules javafx.controls,javafx.fxml
 * Classpath: bin;lib/mysql-connector-j-9.4.0.jar
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set up the database tables
        DatabaseSetup.setupTables();
        
        // Test database connection
        if (!DatabaseConnection.testConnection()) {
            System.err.println("Failed to connect to database. Please check your database settings.");
            return;
        }
        
        // Create a root container that will hold our navigation stack
        StackPane rootContainer = new StackPane();
        rootContainer.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(rootContainer);
        
        // Set up stage properties
        primaryStage.setTitle("Lecturer Management System");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(700);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setAlwaysOnTop(true);  // Force window to front
        primaryStage.show();
        primaryStage.setAlwaysOnTop(false); // Then allow normal behavior
        primaryStage.toFront();
        primaryStage.requestFocus();
        
        // Initialize navigation stack with our root container
        NavigationStack.initialize(rootContainer);
            
        // Start with the login screen
        NavigationStack.push("/View/LoginPage.fxml", "Login");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
