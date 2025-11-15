package Controller;
import Model.CourseCardModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CourseCatalog2 {
    
    @FXML
    private Label CourseTitle;

    @FXML
    private Label LecturerTitle;

    @FXML
    private Label CourseInfo;

    @SuppressWarnings("unused")
    private CourseCardModel courseCard;

    // Populate individual card with data
    public void setCourse(CourseCardModel courseCard) {
        this.courseCard = courseCard;

        // Setting the cards properties using the model's getters
        CourseTitle.setText(courseCard.getCourseTitle());
        LecturerTitle.setText(courseCard.getLecturerTitle());
        CourseInfo.setText(courseCard.getCourseInfo());
    }

    @FXML
    private void handleEnroll() {
        // Pass the selected course back to the previous screen
        NavigationStack.setData(courseCard);
        NavigationStack.pop(); // Return to the lecturer screen
    }
}