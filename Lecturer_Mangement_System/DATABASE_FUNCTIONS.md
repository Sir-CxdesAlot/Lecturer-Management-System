# Lecturer Management System - Database Functions Documentation

## Overview
The Lecturer Management System uses a MySQL database to store and manage lecturer information. The database functions are organized into several key components that handle different aspects of data management.

## Database Structure

### Tables
1. **lecturers** - Main table storing lecturer information
2. **lecturer_courses** - Junction table for lecturer-course relationships
3. **admin** - Admin user credentials (optional)

### Lecturers Table Schema
```sql
CREATE TABLE lecturers (
    lecturer_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    department VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    specialty VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

## Database Connection Management

### DatabaseConnection.java
**Location**: `src/Util/DatabaseConnection.java`

**Purpose**: Manages database connections and provides connection utilities.

#### Key Functions:

1. **getConnection()** ✅ WORKING
   - Returns a connection to the MySQL database
   - Handles driver loading and connection establishment
   - Provides detailed error messages for troubleshooting
   ```java
   public static Connection getConnection() throws SQLException
   ```

2. **testConnection()** ✅ WORKING
   - Tests if database connection is available
   - Returns boolean indicating connection status
   ```java
   public static boolean testConnection()
   ```

#### Configuration:
- **Database URL**: `jdbc:mysql://localhost:3306/lecturer_management`
- **Username**: `root`
- **Password**: `MSI@2025`

## Database Setup and Initialization

### DatabaseSetup.java
**Location**: `src/Util/DatabaseSetup.java`

**Purpose**: Handles database and table creation during application startup.

#### Key Functions:

1. **setupTables()** ✅ WORKING
   - Main entry point for database setup
   - Creates database and all required tables
   ```java
   public static void setupTables()
   ```

2. **createDatabaseIfNotExists()** ✅ WORKING
   - Creates the `lecturer_management` database if it doesn't exist
   - Uses the MySQL root connection to create database

3. **setupLecturerTable()** ✅ WORKING
   - Creates the `lecturers` table with all required fields
   - Creates the `lecturer_courses` junction table
   - Handles foreign key relationships

## Authentication System

### Login Field Database Connection

The login system connects form fields to the database through a multi-layer architecture:

#### 1. FXML Form Fields
**Location**: `src/View/LoginPage.fxml`

The login form contains two key input fields:
```xml
<TextField fx:id="username" ... />
<PasswordField fx:id="password" ... />
```

#### 2. Controller Field Binding
**Location**: `src/Controller/LoginPage.java`

FXML fields are bound to controller variables using `@FXML` annotations:
```java
@FXML
private TextField username;      // Binds to fx:id="username" in FXML
@FXML 
private PasswordField password;  // Binds to fx:id="password" in FXML
```

#### 3. Login Process Flow ✅ WORKING

When the login button is clicked, `handleLogin()` method executes:

1. **Extract Field Values**:
   ```java
   String userInput = username.getText().trim();  // Gets text from username field
   String passInput = password.getText();         // Gets text from password field
   ```

2. **Validate Input**:
   ```java
   if (userInput.isEmpty() || passInput.isEmpty()) {
       showError("Username and password cannot be empty!");
       return;
   }
   ```

3. **Database Authentication**:
   ```java
   AdminDAO adminDAO = new AdminDAO();
   boolean isValid = adminDAO.checkLogin(userInput, passInput);
   ```

#### 4. Admin Table Schema
```sql
CREATE TABLE IF NOT EXISTS admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Default admin account
INSERT INTO admin (username, password) VALUES ('Admin', 'MSI@2025');
```

#### 5. Authentication Flow Summary
```
User enters credentials → TextField/PasswordField → Controller extracts values → 
AdminDAO queries database → Database returns match/no match → 
Controller shows success/error → Navigation to main app or error message
```

#### Default Login Credentials
- **Username**: `Admin`
- **Password**: `MSI@2025`

### AdminDAO.java
**Location**: `src/Data/AdminDAO.java`

**Purpose**: Handles authentication and admin user management.

#### Key Functions:

1. **checkLogin()** ✅ WORKING
   - Validates username and password against admin table
   - Parameters: username (String), password (String)
   - Returns boolean indicating authentication success
   - Includes debug logging for troubleshooting
   ```java
   public boolean checkLogin(String username, String password) throws SQLException
   ```

The authentication system uses JavaFX's FXML binding to connect form fields to controller variables, then processes credentials through the DAO layer to authenticate against the MySQL database.

## Data Access Layer (DAO)

### LecturerDAO.java
**Location**: `src/Data/LecturerDAO.java`

**Purpose**: Provides CRUD (Create, Read, Update, Delete) operations for lecturer data.

#### Key Functions:

1. **addLecturer()** ✅ WORKING
   - Adds a new lecturer to the database
   - Parameters: firstName, lastName, email, department, phone, specialty
   - Uses prepared statements to prevent SQL injection
   ```java
   public void addLecturer(String firstName, String lastName, String email, 
                          String department, String phone, String specialty) throws SQLException
   ```

2. **getAllLecturers()** ✅ WORKING
   - Retrieves all lecturers from the database
   - Returns a List<Lecturer> with all lecturer information
   - Includes console logging for debugging
   ```java
   public List<Lecturer> getAllLecturers() throws SQLException
   ```

3. **updateLecturer()** ✅ WORKING
   - Updates existing lecturer information
   - Parameters: id, firstName, lastName, email, department, phone, specialty
   - Uses lecturer_id to identify the record to update
   ```java
   public void updateLecturer(int id, String firstName, String lastName, String email, 
                             String department, String phone, String specialty) throws SQLException
   ```

4. **deleteLecturer()** ✅ WORKING
   - Deletes a lecturer from the database
   - Parameter: id (lecturer_id)
   - Removes the record permanently
   ```java
   public void deleteLecturer(int id) throws SQLException
   ```

## Model Classes

### Lecturer.java
**Location**: `src/Model/Lecturer.java`

**Purpose**: Data model representing a lecturer entity.

#### Key Features:
- Complete lecturer information storage
- Constructor for new lecturers (without ID)
- Constructor for existing lecturers (with ID)
- Conversion to card model for UI display
- Full getter/setter methods

## Database Integration in Controllers

### Working Database Operations:

1. **Adding Lecturers** ✅
   - `AddLecturerInfo.java` uses `LecturerDAO.addLecturer()`
   - Form data is validated and saved to database
   - Success/error alerts are displayed

2. **Loading Lecturers** ✅
   - `FacultyLecturers.java` uses `LecturerDAO.getAllLecturers()`
   - Lecturer cards are dynamically created from database data
   - Real-time data display in UI

3. **Updating Lecturers** ✅
   - `UpdateLecturerInfo.java` uses `LecturerDAO.updateLecturer()`
   - Existing data is loaded into forms
   - Changes are saved back to database

## Error Handling

### Connection Errors
- MySQL driver not found
- Database connection failures
- Authentication errors
- Database not existing

### SQL Errors
- Constraint violations (duplicate emails)
- Invalid data types
- Foreign key violations

## Security Features

1. **Prepared Statements**: All SQL queries use prepared statements to prevent SQL injection
2. **Connection Management**: Automatic connection closing using try-with-resources
3. **Error Logging**: Comprehensive error messages for debugging

## Usage Examples

### Adding a New Lecturer
```java
LecturerDAO dao = new LecturerDAO();
dao.addLecturer("John", "Doe", "john.doe@university.edu", 
                "Computer Science", "555-1234", "Machine Learning");
```

### Retrieving All Lecturers
```java
LecturerDAO dao = new LecturerDAO();
List<Lecturer> lecturers = dao.getAllLecturers();
for (Lecturer lecturer : lecturers) {
    System.out.println(lecturer.getFirstName() + " " + lecturer.getLastName());
}
```

### Updating Lecturer Information
```java
LecturerDAO dao = new LecturerDAO();
dao.updateLecturer(1, "John", "Smith", "john.smith@university.edu", 
                   "Computer Science", "555-5678", "Artificial Intelligence");
```

## Current Status

### ✅ Working Functions:
- Database connection establishment
- Database and table creation
- User authentication and login system
- Adding new lecturers
- Retrieving all lecturers
- Updating lecturer information
- Deleting lecturers
- Data validation and error handling
- Admin user management

### 🔧 Areas for Enhancement:
- Course assignment functionality
- Data export/import
- Advanced search and filtering
- Audit logging
- Backup and recovery
- Password encryption for admin accounts

## Troubleshooting

### Common Issues:
1. **MySQL not running**: Start MySQL server
2. **Wrong credentials**: Update username/password in DatabaseConnection.java
3. **Driver not found**: Ensure mysql-connector-j-*.jar is in lib folder
4. **Port conflicts**: Check if MySQL is running on port 3306

### Debug Information:
The system provides console output for:
- Database connection attempts
- SQL query execution
- Record counts
- Error details

This documentation covers all the currently working database functions in the Lecturer Management System.