# DAO Pattern Documentation - Lecturer Management System

## What is DAO?

**DAO (Data Access Object)** is a fundamental design pattern in software engineering that provides an abstract interface to access data from a database. It serves as a bridge between your application's business logic and the data persistence layer.

### Key Principles of DAO Pattern:

1. **Separation of Concerns**: Isolates data access logic from business logic
2. **Abstraction**: Provides a clean interface for database operations
3. **Encapsulation**: Hides the complexity of database operations
4. **Maintainability**: Makes code easier to modify and extend
5. **Testability**: Allows independent testing of data access logic

### DAO Pattern Structure:
```
Application Layer (Controllers)
        ↓
    DAO Layer (Data Access Objects)
        ↓
    Database Layer (MySQL)
```

## Benefits of Using DAO Pattern

### 1. **Code Organization**
- All database operations for a specific entity are centralized in one class
- Clear separation between UI logic and data access logic
- Easier to locate and modify database-related code

### 2. **Reusability**
- Multiple controllers can use the same DAO methods
- Reduces code duplication across the application
- Consistent data access patterns throughout the system

### 3. **Database Independence**
- Easy to switch between different database systems
- Database-specific code is isolated to DAO classes
- Business logic remains unchanged when database changes

### 4. **Error Handling**
- Centralized error handling for database operations
- Consistent exception management across data access operations
- Better debugging and logging capabilities

### 5. **Security**
- Prepared statements prevent SQL injection attacks
- Centralized security measures for data access
- Controlled access to sensitive database operations

## DAO Files in Lecturer Management System

### 1. AdminDAO.java
**Location**: `src/Data/AdminDAO.java`

**Purpose**: Handles authentication and admin user management operations.

#### Class Overview:
```java
public class AdminDAO {
    // Methods for admin-related database operations
}
```

#### Key Methods:

##### `checkLogin(String username, String password)` ✅ WORKING
- **Purpose**: Validates admin credentials against the database
- **Parameters**: 
  - `username` (String): Admin username to verify
  - `password` (String): Admin password to verify
- **Returns**: `boolean` - true if credentials are valid, false otherwise
- **Throws**: `SQLException` if database error occurs

**Implementation Details**:
```java
public boolean checkLogin(String username, String password) throws SQLException {
    String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, username);
        stmt.setString(2, password);
        
        try (ResultSet rs = stmt.executeQuery()) {
            return rs.next(); // Returns true if matching record found
        }
    }
}
```

**Usage Example**:
```java
AdminDAO adminDAO = new AdminDAO();
boolean isValid = adminDAO.checkLogin("Admin", "MSI@2025");
if (isValid) {
    // Login successful - navigate to main application
    NavigationStack.push("/View/FacultyLecturers.fxml", "Faculty Lecturers");
} else {
    // Login failed - show error message
    showError("Invalid username or password!");
}
```

**Security Features**:
- Uses prepared statements to prevent SQL injection
- Includes debug logging for troubleshooting
- Validates input parameters before database query

---

### 2. LecturerDAO.java
**Location**: `src/Data/LecturerDAO.java`

**Purpose**: Provides complete CRUD (Create, Read, Update, Delete) operations for lecturer data management.

#### Class Overview:
```java
public class LecturerDAO {
    // Methods for lecturer-related database operations
}
```

#### Key Methods:

##### `addLecturer(String firstName, String lastName, String email, String department, String phone, String specialty)` ✅ WORKING
- **Purpose**: Adds a new lecturer record to the database
- **Parameters**: 
  - `firstName` (String): Lecturer's first name
  - `lastName` (String): Lecturer's last name
  - `email` (String): Lecturer's email address (must be unique)
  - `department` (String): Academic department
  - `phone` (String): Contact phone number (optional)
  - `specialty` (String): Area of expertise (optional)
- **Returns**: `void`
- **Throws**: `SQLException` if database error or constraint violation occurs

**Implementation Details**:
```java
public void addLecturer(String firstName, String lastName, String email, 
                       String department, String phone, String specialty) throws SQLException {
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
```

##### `getAllLecturers()` ✅ WORKING
- **Purpose**: Retrieves all lecturer records from the database
- **Parameters**: None
- **Returns**: `List<Lecturer>` - List of all lecturers in the system
- **Throws**: `SQLException` if database error occurs

**Implementation Details**:
```java
public List<Lecturer> getAllLecturers() throws SQLException {
    List<Lecturer> lecturers = new ArrayList<>();
    String sql = "SELECT * FROM lecturers";
    
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
            
            Lecturer lecturer = new Lecturer(id, firstName, lastName, email, department, phone, specialty);
            lecturers.add(lecturer);
        }
    }
    return lecturers;
}
```

##### `updateLecturer(int id, String firstName, String lastName, String email, String department, String phone, String specialty)` ✅ WORKING
- **Purpose**: Updates existing lecturer information in the database
- **Parameters**: 
  - `id` (int): Unique lecturer ID to identify the record
  - `firstName` (String): Updated first name
  - `lastName` (String): Updated last name
  - `email` (String): Updated email address
  - `department` (String): Updated department
  - `phone` (String): Updated phone number
  - `specialty` (String): Updated specialty
- **Returns**: `void`
- **Throws**: `SQLException` if database error occurs

**Implementation Details**:
```java
public void updateLecturer(int id, String firstName, String lastName, String email, 
                          String department, String phone, String specialty) throws SQLException {
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
```

##### `deleteLecturer(int id)` ✅ WORKING
- **Purpose**: Removes a lecturer record from the database
- **Parameters**: 
  - `id` (int): Unique lecturer ID to identify the record for deletion
- **Returns**: `void`
- **Throws**: `SQLException` if database error occurs

**Implementation Details**:
```java
public void deleteLecturer(int id) throws SQLException {
    String sql = "DELETE FROM lecturers WHERE lecturer_id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
```

## DAO Usage in Controllers

### How Controllers Interact with DAOs:

#### 1. **LoginPage Controller → AdminDAO**
```java
public class LoginPage {
    @FXML
    private void handleLogin() {
        String username = this.username.getText().trim();
        String password = this.password.getText();
        
        try {
            AdminDAO adminDAO = new AdminDAO();
            boolean isValid = adminDAO.checkLogin(username, password);
            
            if (isValid) {
                NavigationStack.push("/View/FacultyLecturers.fxml", "Faculty Lecturers");
            } else {
                showError("Invalid username or password!");
            }
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
        }
    }
}
```

#### 2. **AddLecturerInfo Controller → LecturerDAO**
```java
public class AddLecturerInfo {
    @FXML
    private void handleSubmit(ActionEvent event) {
        try {
            String[] names = staffNameField.getText().trim().split(" ", 2);
            String firstName = names[0];
            String lastName = names.length > 1 ? names[1] : "";

            LecturerDAO lecturerDAO = new LecturerDAO();
            lecturerDAO.addLecturer(
                firstName,
                lastName,
                staffNumberField.getText().trim(),
                buildingLocationField.getText().trim(),
                phoneField.getText().trim(),
                specialtyField.getText().trim()
            );

            // Show success message and navigate back
            showSuccessMessage("Lecturer added successfully!");
            NavigationStack.pop();
        } catch (SQLException e) {
            showError("Failed to add lecturer: " + e.getMessage());
        }
    }
}
```

#### 3. **FacultyLecturers Controller → LecturerDAO**
```java
public class FacultyLecturers {
    public void loadLecturerCards() {
        try {
            LecturerDAO lecturerDAO = new LecturerDAO();
            List<Lecturer> lecturers = lecturerDAO.getAllLecturers();
            
            // Clear existing cards
            cardContainer.getChildren().clear();
            
            // Create cards for each lecturer
            for (Lecturer lecturer : lecturers) {
                // Create and add lecturer card to UI
                createLecturerCard(lecturer);
            }
        } catch (SQLException e) {
            showError("Failed to load lecturers: " + e.getMessage());
        }
    }
}
```

## DAO Best Practices in the System

### 1. **Resource Management**
- Uses try-with-resources statements for automatic connection closing
- Proper cleanup of PreparedStatements and ResultSets
- No resource leaks in database operations

### 2. **Error Handling**
- Comprehensive SQLException handling
- Meaningful error messages for debugging
- Graceful degradation when database operations fail

### 3. **Security**
- All queries use prepared statements to prevent SQL injection
- Input validation before database operations
- Secure credential handling

### 4. **Performance**
- Efficient query design
- Proper indexing on database tables
- Connection pooling through DatabaseConnection utility

### 5. **Maintainability**
- Clear method names that describe their purpose
- Comprehensive documentation and comments
- Consistent coding patterns across all DAO classes

## DAO Pattern Benefits in This System

### 1. **Modularity**
Each DAO handles operations for a specific entity (Admin, Lecturer), making the code organized and focused.

### 2. **Reusability**
Multiple controllers can use the same DAO methods without duplicating database logic.

### 3. **Testability**
DAO methods can be unit tested independently of the UI layer.

### 4. **Maintainability**
Database schema changes only require updates to the relevant DAO classes.

### 5. **Scalability**
Easy to add new entities by creating additional DAO classes following the same pattern.

## Future DAO Enhancements

### Potential Additional DAO Classes:

1. **CourseDAO.java** - For course management operations
2. **UserDAO.java** - For expanded user management beyond admin
3. **ReportDAO.java** - For generating reports and analytics
4. **AuditDAO.java** - For tracking system changes and user actions

### Enhanced Features:
- Connection pooling for better performance
- Caching mechanisms for frequently accessed data
- Batch operations for bulk data processing
- Transaction management for complex operations

The DAO pattern in this Lecturer Management System provides a solid foundation for data access operations, ensuring clean separation of concerns, security, and maintainability throughout the application.