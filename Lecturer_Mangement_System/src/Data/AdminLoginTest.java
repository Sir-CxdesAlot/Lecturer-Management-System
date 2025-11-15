package Data;

import java.sql.*;
import Util.DatabaseConnection;

/**
 * Test the admin login
 */
public class AdminLoginTest {
    public static void main(String[] args) {
        try {
            // First create the admin table and insert the admin user
            try (Connection conn = DatabaseConnection.getConnection()) {
                // Create table
                String createTable = "CREATE TABLE IF NOT EXISTS admin (" +
                                   "username VARCHAR(50) PRIMARY KEY, " +
                                   "password VARCHAR(50) NOT NULL)";
                conn.createStatement().execute(createTable);
                
                // Insert admin (ignore if already exists)
                String insertAdmin = "INSERT IGNORE INTO admin (username, password) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(insertAdmin);
                stmt.setString(1, "Admin");
                stmt.setString(2, "MSI@2025");
                stmt.execute();
                
                System.out.println("Admin table and user created successfully!");
            }
            
            AdminDAO adminDAO = new AdminDAO();
            
            // Test correct login
            System.out.println("Testing correct admin login...");
            boolean validLogin = adminDAO.checkLogin("Admin", "MSI@2025");
            System.out.println("Login successful: " + validLogin);
            
            // Test wrong password
            System.out.println("\nTesting wrong password...");
            boolean invalidLogin = adminDAO.checkLogin("Admin", "wrongpassword");
            System.out.println("Login successful: " + invalidLogin);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}