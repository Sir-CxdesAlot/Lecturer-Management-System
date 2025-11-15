package Controller;

import javafx.scene.layout.StackPane;
import java.util.Stack;
import java.io.IOException;

/**
 * NavigationStack - Core navigation system for the Lecturer Management System
 * 
 * This class manages navigation between FXML screens using a Stack data structure.
 * Instead of creating new windows or replacing the entire Scene, it maintains a history
 * of screens that you can navigate through using push() and pop(), similar to how
 * mobile apps or web browsers work.
 *
 * Key concepts:
 * - Stack<NavigationState>: Stores the screen history (LIFO - Last In, First Out)
 * - StackPane root: The container where screens are displayed
 * - FXML resources: Loaded from /View/ folder using absolute paths
 *
 * Usage example in a controller:
 *   @FXML
 *   private void handleAddCourse() {
 *       NavigationStack.push("/View/AddCourseInfo.fxml", "Add Course");
 *   }
 *
 *   @FXML
 *   private void handleBack() {
 *       NavigationStack.pop();  // Returns to previous screen
 *   }
 */
public class NavigationStack {
    /**
     * The navigation stack holds NavigationState objects in LIFO order.
     * - push() adds a new screen on top
     * - pop() removes the current screen and shows the previous
     * - peek() looks at current screen without changing anything
     *
     * Each NavigationState contains:
     * 1. The loaded FXML root node (what you see on screen)
     * 2. The controller instance (handles user actions)
     * 3. The screen's title
     */
    private static Stack<NavigationState> navigationStack = new Stack<>();
    private static Object data;  // Data to pass between screens
    
    public static void setData(Object data) {
        NavigationStack.data = data;
    }
    
    public static Object getData() {
        return data;
    }

    /**
     * Returns the controller of the current screen.
     * @return The current screen's controller or null if no screens
     */
    public static Object getCurrentController() {
        if (!navigationStack.isEmpty()) {
            return navigationStack.peek().getController();
        }
        return null;
    }

    /**
     * The root StackPane container from Main.java's primary Stage.
     * This is where we display the current screen's content.
     * Only the top screen of the stack is visible here at any time.
     *
     * Technical note: All modifications to this must happen on the
     * JavaFX Application Thread since it's a UI control.
     */
    private static StackPane rootContainer;

    /**
     * Called once from Main.java to set up the root container where screens will appear.
     * This must be called before any push/pop operations.
     *
     * @param container The StackPane that will hold all screen content
     */
    public static void initialize(StackPane container) {
        rootContainer = container;
    }

    /**
     * Push a new screen onto the navigation stack and display it.
     *
     * This is the main way to navigate forward to a new screen. It:
     * 1. Loads the FXML file from the given path
     * 2. Creates a NavigationState to hold the loaded screen
     * 3. Pushes that state onto the stack
     * 4. Shows the new screen in the root container
     *
     * Example usage from a controller:
     *   NavigationStack.push("/View/AddCourseInfo.fxml", "Add Course");
     *
     * Note: Always use absolute paths (starting with /) from the classpath root.
     * The FXML file must be in your resources (typically under /View/).
     *
     * @param fxmlPath The resource path to the FXML file (e.g., "/View/Welcome.fxml")
     * @param title The title for this screen (shown in UI or used for debugging)
     */
    public static void push(String fxmlPath, String title) {
        try {
            // Create new state (loads FXML and instantiates its controller)
            NavigationState state = new NavigationState(fxmlPath, title);
            
            // Add to stack (now becomes the "current" screen)
            navigationStack.push(state);
            
            // Show the new screen (updates the StackPane children)
            updateDisplay();
        } catch (IOException e) {
            System.err.println("Failed to load FXML: " + fxmlPath);
            System.err.println("Common causes:");
            System.err.println("1. Wrong path - ensure it starts with /View/");
            System.err.println("2. File missing from resources/build output");
            System.err.println("3. Controller class not found");
            e.printStackTrace();
        }
    }

    /**
     * Remove the current screen and go back to the previous one.
     *
     * This is how you implement "back" navigation. It:
     * 1. Checks if we can go back (more than 1 screen in history)
     * 2. Removes the current screen from the stack
     * 3. Shows the previous screen that's now on top
     *
     * Example usage in a controller:
     *   @FXML
     *   private void handleBack() {
     *       if (NavigationStack.canGoBack()) {
     *           NavigationStack.pop();
     *       }
     *   }
     */
    public static void pop() {
        // Only pop if there's a screen to go back to
        if (canGoBack()) {
            navigationStack.pop();  // Remove current screen
            updateDisplay();        // Show previous screen
        }
    }

    /**
     * Update which screen is visible in the UI.
     * 
     * This internal method:
     * 1. Clears the root container (removes old screen)
     * 2. Adds the current screen's root node from the stack
     *
     * Note: This must run on the JavaFX Application Thread since
     * it modifies UI controls (the StackPane children).
     */
    private static void updateDisplay() {
        if (!navigationStack.isEmpty()) {
            NavigationState currentState = navigationStack.peek();
            rootContainer.getChildren().clear();
            rootContainer.getChildren().add(currentState.getRoot());
        }
    }

    /**
     * Check if there's a previous screen to go back to.
     *
     * Returns true if there's more than one screen in the stack.
     * Use this to enable/disable back buttons or prevent popping
     * the last (root) screen.
     *
     * @return true if pop() will succeed, false if at root screen
     */
    public static boolean canGoBack() {
        return navigationStack.size() > 1;
    }

    /**
     * Clear navigation history except for the root screen.
     *
     * This is useful for "log out" or "go home" operations where
     * you want to remove all screens except the first one pushed.
     */
    public static void clearStack() {
        // Keep popping until only one screen remains (the root)
        while (navigationStack.size() > 1) {
            navigationStack.pop();
        }
        updateDisplay();  // Show the root screen
    }

    /**
     * Get the current screen's NavigationState without changing anything.
     *
     * Use this to:
     * - Get the current screen's controller
     * - Check what screen is showing
     * - Access the current FXML root node
     *
     * @return The current NavigationState, or null if stack is empty
     */
    public static NavigationState getCurrentState() {
        return navigationStack.isEmpty() ? null : navigationStack.peek();
    }

    /**
     * Alias for getCurrentState() - returns current screen state.
     * 
     * This matches the standard Stack.peek() operation name.
     * Use this to look at the current screen's state without
     * changing navigation history.
     *
     * @return The current NavigationState, or null if stack is empty
     */
    public static NavigationState peek() {
        return getCurrentState();
    }
}