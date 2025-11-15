package Data;

import Model.Lecturer;
import Util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple database operations for Lecturers
 * Handles basic CRUD (Create, Read, Update, Delete) operations
 */
public class LecturerDAO {
    
    /**
     * Add a new lecturer to the database
     */
    public void addLecturer(String firstName, String lastName, String email, String department, String phone, String specialty) throws SQLException {
        String sql = "INSERT INTO lecturers (first_name, last_name, email, department, phone, specialty) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, department);
            stmt.setString(5, phone);
            stmt.setString(6, specialty);
            stmt.executeUpdate();
        }
    }
    
    /**
     * Get all lecturers from the database
     */
    public List<Lecturer> getAllLecturers() throws SQLException {
        List<Lecturer> lecturers = new ArrayList<>();
        String sql = "SELECT * FROM lecturers";
        System.out.println("Attempting to load lecturers from database...");
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                int id = rs.getInt("lecturer_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String department = rs.getString("department");
                String phone = rs.getString("phone");
                String specialty = rs.getString("specialty");
                
                System.out.println("Found lecturer: " + firstName + " " + lastName);
                
                Lecturer lecturer = new Lecturer(id, firstName, lastName, email, department, phone, specialty);
                lecturers.add(lecturer);
            }
            System.out.println("Total lecturers found: " + lecturers.size());
        }
        return lecturers;
    }
    
    /**
     * Update a lecturer's information
     */
    public void updateLecturer(int id, String firstName, String lastName, String email, String department, String phone, String specialty) throws SQLException {
        String sql = "UPDATE lecturers SET first_name = ?, last_name = ?, email = ?, department = ?, phone = ?, specialty = ? WHERE lecturer_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, department);
            stmt.setString(5, phone);
            stmt.setString(6, specialty);
            stmt.setInt(7, id);
            stmt.executeUpdate();
        }
    }
    
    /**
     * Delete a lecturer from the database
     */
    public void deleteLecturer(int id) throws SQLException {
        String sql = "DELETE FROM lecturers WHERE lecturer_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}