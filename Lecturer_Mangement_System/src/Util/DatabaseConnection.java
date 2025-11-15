package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Simple database connection helper
 * Uses MySQL JDBC driver to connect to the database
 */
public class DatabaseConnection {
    // Update these with your MySQL settings
    private static final String URL = "jdbc:mysql://localhost:3306/lecturer_management?createDatabaseIfNotExist=true";
    public static final String USER = "root";     // Your MySQL username
    public static final String PASSWORD = "MSI@2025";      // Your MySQL password
    
    /**
     * Get a connection to the database
     */
    public static Connection getConnection() throws SQLException {
        try {
            // This line will verify the MySQL driver is available
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            System.out.println("Check that mysql-connector-j-*.jar is in your lib folder");
            throw new SQLException("MySQL JDBC Driver not found", e);
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            System.out.println("Check that:");
            System.out.println("1. MySQL is running");
            System.out.println("2. Your username and password are correct");
            System.out.println("3. The database 'lecturer_management' exists");
            throw e;
        }
    }

    /**
     * Test if we can connect to the database
     */
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}