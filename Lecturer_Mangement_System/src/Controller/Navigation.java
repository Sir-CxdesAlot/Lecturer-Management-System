package Controller;

/**
 * IMPORTANT: This class is DEPRECATED and should not be used!
 * 
 * The Navigation helper has been replaced by NavigationStack for better control
 * and simpler navigation using a proper Stack data structure.
 *
 * How to migrate your code:
 *
 * 1. Replace Navigation.navigateTo() calls with NavigationStack.push():
 *    Old: Navigation.navigateTo("LoginPage.fxml");
 *    New: NavigationStack.push("/View/LoginPage.fxml", "Login");
 *
 * 2. Replace Navigation.goBack() with NavigationStack.pop():
 *    Old: Navigation.goBack();
 *    New: NavigationStack.pop();
 *
 * 3. For checking if you can go back:
 *    Old: if (Navigation.canGoBack()) ...
 *    New: if (NavigationStack.canGoBack()) ...
 *
 * 4. Remove any Stage-specific code - NavigationStack handles all UI updates
 *    through its internal StackPane root container.
 *
 * Example of proper navigation in a controller:
 *
 * @FXML
 * private void handleLogin() {
 *     // Push new screen
 *     NavigationStack.push("/View/Welcome.fxml", "Welcome");
 * }
 *
 * @FXML
 * private void handleBack() {
 *     // Go back to previous screen
 *     if (NavigationStack.canGoBack()) {
 *         NavigationStack.pop();
 *     }
 * }
 *
 * @see NavigationStack for the new navigation API
 * @deprecated Use NavigationStack instead - this class will be removed
 */
@Deprecated
public class Navigation {
    /**
     * Throws an error to prevent accidental use of the old Navigation helper.
     * @throws UnsupportedOperationException always
     */
    public static void unsupported() {
        throw new UnsupportedOperationException(
            "Navigation is deprecated. Use NavigationStack instead:\n" +
            "- Push screen: NavigationStack.push(\"/View/Screen.fxml\", \"Title\")\n" +
            "- Go back: NavigationStack.pop()\n" +
            "See NavigationStack.java for full documentation."
        );
    }
}
