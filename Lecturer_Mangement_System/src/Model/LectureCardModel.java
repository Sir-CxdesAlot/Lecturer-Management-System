package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Util.DatabaseConnection;

public class LectureCardModel {
    private int lecturerId;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String phone;
    private String specialty;
    private String lecturerTitle;
    private String lecturerOffice;
    private String lecturerCourse;
    
    // Constructors
    public LectureCardModel() {
        // Default constructor
    }
    
    public LectureCardModel(String lecturerTitle, String lecturerOffice, String lecturerCourse) {
        this.lecturerTitle = lecturerTitle;
        this.lecturerOffice = lecturerOffice;
        this.lecturerCourse = lecturerCourse;
        // Extract first and last name from title
        String[] names = lecturerTitle.split(" ", 2);
        if (names.length > 0) this.firstName = names[0];
        if (names.length > 1) this.lastName = names[1];
        // Extract department from office
        if (lecturerOffice != null && lecturerOffice.endsWith(" Department")) {
            this.department = lecturerOffice.substring(0, lecturerOffice.length() - " Department".length());
        }
        this.email = lecturerCourse; // Since we're temporarily using email as course
    }

    public LectureCardModel(int lecturerId, String firstName, String lastName, String email, 
                          String department, String phone, String specialty) {
        this.lecturerId = lecturerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.phone = phone;
        this.specialty = specialty;
    }
    
    // Getters
    public int getLecturerId() { return lecturerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getPhone() { return phone; }
    public String getSpecialty() { return specialty; }
    public String getLecturerTitle() { return lecturerTitle; }
    public String getLecturerOffice() { return lecturerOffice; }
    public String getLecturerCourse() { return lecturerCourse; }
    
    // Setters
    public void setLecturerId(int id) { this.lecturerId = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setDepartment(String department) { this.department = department; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public void setLecturerTitle(String title) { this.lecturerTitle = title; }
    public void setLecturerOffice(String office) { this.lecturerOffice = office; }
    public void setLecturerCourse(String course) { this.lecturerCourse = course; }
    
    // Database operations
    public boolean updateInDatabase() throws SQLException {
        String sql = "UPDATE lecturers SET first_name=?, last_name=?, email=?, " +
                    "department=?, phone=?, specialty=? WHERE lecturer_id=?";
                    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, department);
            stmt.setString(5, phone);
            stmt.setString(6, specialty);
            stmt.setInt(7, lecturerId);
            
            return stmt.executeUpdate() > 0;
        }
    }
    
}