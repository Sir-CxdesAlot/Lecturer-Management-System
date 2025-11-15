package Util;

/**
 * Simple test to verify database connection works
 */
public class DatabaseTest {
    public static void main(String[] args) {
        System.out.println("Testing database connection...");
        
        if (DatabaseConnection.testConnection()) {
            System.out.println("Success! Connected to the database.");
        } else {
            System.out.println("Failed to connect to the database.");
            System.out.println("Make sure:");
            System.out.println("1. MySQL is running");
            System.out.println("2. The password in DatabaseConnection.java is correct");
            System.out.println("3. The database 'lecturer_management' exists");
        }
    }
}