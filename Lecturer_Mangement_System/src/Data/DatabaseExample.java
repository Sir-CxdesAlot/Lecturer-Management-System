package Data;

/**
 * Test the simplified lecturer database operations
 */
public class DatabaseExample {
    public static void main(String[] args) {
        try {
            // Create our data access object
            LecturerDAO lecturerDAO = new LecturerDAO();
            
            // Add a new lecturer
            System.out.println("Adding a new lecturer...");
            lecturerDAO.addLecturer(
                "John",                      // firstName
                "Doe",                      // lastName
                "john.doe.new@example.com", // email (unique)
                "Computer Science",         // department
                "000-000-0000",             // phone (placeholder)
                "General"                   // specialty (placeholder)
            );
            
            // Get all lecturers and print them
            System.out.println("\nAll lecturers in database:");
            for (Model.Lecturer lecturer : lecturerDAO.getAllLecturers()) {
                System.out.println(lecturer.getFullName() + " - " + lecturer.getDepartment());
            }
            
            // Update a lecturer (assuming ID 1 exists)
            System.out.println("\nUpdating lecturer with ID 1...");
            lecturerDAO.updateLecturer(
                1,                           // id
                "John",                     // firstName
                "Smith",                    // lastName (changed)
                "john.smith@example.com",   // email (changed)
                "Mathematics",              // department (changed)
                "000-000-0000",             // phone (placeholder)
                "General"                   // specialty (placeholder)
            );
            
            // Delete a lecturer (assuming ID 2 exists)
            System.out.println("\nDeleting lecturer with ID 2...");
            lecturerDAO.deleteLecturer(2);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}