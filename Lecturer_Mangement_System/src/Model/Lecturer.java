package Model;

/**
 * Simple Lecturer model class
 */
public class Lecturer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String phone;
    private String specialty;
    
    public LectureCardModel toCardModel() {
        // Create a card model with all lecturer information
        LectureCardModel model = new LectureCardModel(id, firstName, lastName, email, department, phone, specialty);
        // Set display values for the card view
        model.setLecturerTitle(firstName + " " + lastName);
        model.setLecturerOffice(department + " Department");
        model.setLecturerCourse(email); // We'll update this when we add course assignments
        return model;
    }
    
    // Constructor for new lecturers (no id)
    public Lecturer(String firstName, String lastName, String email, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
    }
    
    // Constructor for existing lecturers (with id)
    public Lecturer(int id, String firstName, String lastName, String email, String department, String phone, String specialty) {
        this(firstName, lastName, email, department);
        this.id = id;
        this.phone = phone;
        this.specialty = specialty;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    // Convenience method to get full name
    public String getFullName() {
        return firstName + " " + lastName;
    }
}