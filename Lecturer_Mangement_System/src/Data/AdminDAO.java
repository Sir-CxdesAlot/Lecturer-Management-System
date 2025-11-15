package Data;

import Util.DatabaseConnection;
import java.sql.*;

/**
 * Simple admin login checker
 */
public class AdminDAO {
    
    /**
     * Check if login credentials are valid
     * @return true if username and password match the admin account
     */
    public boolean checkLogin(String username, String password) throws SQLException {
        // First check what admins exist in the database
        try (Connection conn = DatabaseConnection.getConnection();
             Statement checkStmt = conn.createStatement();
             ResultSet checkRs = checkStmt.executeQuery("SELECT username, password FROM admin")) {
            
            System.out.println("Current admins in database:");
            while (checkRs.next()) {
                System.out.println("Username: " + checkRs.getString("username") + 
                                 ", Password: " + checkRs.getString("password"));
            }
        }

        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            System.out.println("Attempting login with - Username: " + username + ", Password: " + password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                boolean found = rs.next();
                System.out.println("Login " + (found ? "successful" : "failed"));
                return found;
            }
        }
    }
}