package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

/**
 * NavigationState - Represents one screen in the navigation history
 *
 * This class holds everything needed to display and manage a screen:
 * 1. The FXML file path that created it
 * 2. The loaded UI elements (JavaFX Parent node)
 * 3. The controller instance that handles events
 * 4. A title for the screen
 *
 * When NavigationStack.push() is called, it creates a new NavigationState
 * which loads the FXML file and keeps everything together. This state
 * object then sits on the navigation stack until needed.
 *
 * Example of what happens in memory:
 *   Welcome Screen State:        Course Catalog State:
 *   - fxmlPath: /View/Welcome   - fxmlPath: /View/CourseCatalog
 *   - title: "Welcome"          - title: "Courses"
 *   - root: [Welcome UI node]   - root: [Catalog UI node]
 *   - controller: WelcomeCtrl   - controller: CatalogCtrl
 */
public class NavigationState {
    /** The FXML resource path that was used to create this screen */
    private final String fxmlPath;
    
    /** The screen's title (shown in UI or used for debugging) */
    private final String title;
    
    /** The controller instance created by FXMLLoader */
    private final Object controller;
    
    /** The root UI node loaded from FXML - what you see on screen */
    private final Parent root;

    /**
     * Create a new screen state by loading an FXML file.
     *
     * This constructor:
     * 1. Saves the FXML path and title
     * 2. Creates an FXMLLoader to load the UI
     * 3. Loads the FXML file (creates all UI elements)
     * 4. Gets the controller instance that FXMLLoader created
     *
     * Important notes:
     * - The FXML path must start with / (e.g., "/View/Welcome.fxml")
     * - The FXML file must be on the classpath (in resources)
     * - The FXML must specify fx:controller="..." to create a controller
     *
     * @param fxmlPath Resource path to the FXML file
     * @param title Screen title (for UI/debugging)
     * @throws IOException if FXML file not found or has errors
     */
    public NavigationState(String fxmlPath, String title) throws IOException {
        this.fxmlPath = fxmlPath;
        this.title = title;
        
        // Load the FXML file and create the UI
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        this.root = loader.load();
        
        // Get the controller that was created while loading FXML
        this.controller = loader.getController();
    }

    /**
     * Get the FXML path that created this screen.
     * Useful for debugging or recreating the screen.
     */
    public String getFxmlPath() {
        return fxmlPath;
    }

    /**
     * Get this screen's title.
     * Can be shown in UI or used for logging.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the controller instance for this screen.
     * Cast this to the specific controller type you need:
     *
     * Example:
     *   CourseCatalog controller = (CourseCatalog)state.getController();
     *   controller.refreshCards();
     */
    public Object getController() {
        return controller;
    }

    /**
     * Get the root UI node for this screen.
     * This is what NavigationStack shows in the StackPane.
     */
    public Parent getRoot() {
        return root;
    }
}