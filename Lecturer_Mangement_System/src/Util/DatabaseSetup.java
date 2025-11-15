package Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 * Utility to run SQL scripts to set up the database
 */
public class DatabaseSetup {
    public static void setupTables() {
        // First, ensure the database exists
        createDatabaseIfNotExists();
        
        // Then setup individual tables if needed
        setupAdminTable();
        setupLecturerTable();
    }
    
    private static void createDatabaseIfNotExists() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", 
                                                        DatabaseConnection.USER, 
                                                        DatabaseConnection.PASSWORD);
             Statement stmt = conn.createStatement()) {
            
            System.out.println("Creating database if not exists...");
            stmt.execute("CREATE DATABASE IF NOT EXISTS lecturer_management");
            stmt.execute("USE lecturer_management");
            System.out.println("Database setup complete!");
            
        } catch (SQLException e) {
            System.err.println("Failed to create database:");
            e.printStackTrace();
        }
    }

    private static void executeSchemaFile(String resourcePath, String description) {
        executeSqlFile(resourcePath, description);
    }

    private static void setupAdminTable() {
        // executeSqlFile("/Data/admin.sql", "Admin");
        // TODO: Fix admin.sql resource path
        System.out.println("Skipping admin table setup (SQL file not found)");
    }

    private static void setupLecturerTable() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Create lecturers table first
            System.out.println("Creating lecturers table...");
            stmt.execute("CREATE TABLE IF NOT EXISTS lecturers (" +
                        "lecturer_id INT PRIMARY KEY AUTO_INCREMENT," +
                        "first_name VARCHAR(50) NOT NULL," +
                        "last_name VARCHAR(50) NOT NULL," +
                        "email VARCHAR(100) NOT NULL UNIQUE," +
                        "department VARCHAR(100) NOT NULL," +
                        "phone VARCHAR(20)," +
                        "specialty VARCHAR(100)," +
                        "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                        "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                        ")");
            
            // Then create lecturer_courses table that references lecturers
            System.out.println("Creating lecturer_courses table...");
            stmt.execute("CREATE TABLE IF NOT EXISTS lecturer_courses (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT," +
                        "lecturer_id INT," +
                        "course_id INT," +
                        "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                        "FOREIGN KEY (lecturer_id) REFERENCES lecturers(lecturer_id)" +
                        ")");
            
            System.out.println("Lecturer tables setup complete!");
        } catch (SQLException e) {
            System.err.println("Failed to set up lecturer tables:");
            e.printStackTrace();
        }
    }

    private static void executeSqlFile(String resourcePath, String tableName) {
        System.out.println("Setting up " + tableName + " using " + resourcePath);
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Read the SQL file
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    DatabaseSetup.class.getResourceAsStream(resourcePath)))) {
                
                StringBuilder sql = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    // Skip comments and empty lines when logging
                    if (!line.trim().startsWith("--") && !line.trim().isEmpty()) {
                        System.out.println("Executing SQL: " + line.trim());
                    }
                    sql.append(line);
                    if (line.contains(";")) {
                        String sqlCommand = sql.toString();
                        System.out.println("Executing complete command: " + sqlCommand);
                        stmt.execute(sqlCommand);
                        sql.setLength(0);
                    }
                }
                System.out.println(tableName + " table setup complete!");
            }
        } catch (Exception e) {
            System.err.println("Failed to set up " + tableName.toLowerCase() + " table:");
            e.printStackTrace();
        }
    }
}