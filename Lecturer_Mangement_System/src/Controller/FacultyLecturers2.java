package Controller;
import Model.LectureCardModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FacultyLecturers2 {

    @FXML private Label staffName;
    @FXML private Label staffNumber;
    @FXML private Label officeNumber;
    @FXML private Label buildingLocation;
    @FXML private Label phoneNumber;
    @FXML private Label specialty;

    @SuppressWarnings("unused")
    private LectureCardModel lecturerCard; 

    // Populate individual card with data
    public void setLecturer(LectureCardModel lecturerCard) {
        this.lecturerCard = lecturerCard;

        // Setting the cards properties using the model's getters
        staffName.setText(lecturerCard.getFirstName() + " " + lecturerCard.getLastName());
        staffNumber.setText("Staff Number: " + lecturerCard.getLecturerId());
        officeNumber.setText("Office Number: " + lecturerCard.getDepartment());
        buildingLocation.setText("Building Location: " + lecturerCard.getEmail());
        phoneNumber.setText("Phone: " + (lecturerCard.getPhone() != null ? lecturerCard.getPhone() : "Not provided"));
        specialty.setText("Specialty: " + (lecturerCard.getSpecialty() != null ? lecturerCard.getSpecialty() : "Not provided"));
    }

    @FXML
    private void handleEdit() {
        // Make sure we have lecturer data to edit
        if (lecturerCard != null) {
            // Set the lecturer data to be passed to the edit screen
            NavigationStack.setData(lecturerCard);
            // Navigate to the update screen
            NavigationStack.push("/View/UpdateLecturerInfo.fxml", "Update Lecturer Info");
        }
    }

    @FXML
    private void handleDelete() {
        // Logic to delete the lecturer can be added here
        // Pass the selected lecturer model to the confirmation screen
        if (lecturerCard != null) {
            NavigationStack.setData(lecturerCard);
        }
        NavigationStack.push("/View/DeletionConfirmation.fxml", "Deletion Confirmation");
    }
}