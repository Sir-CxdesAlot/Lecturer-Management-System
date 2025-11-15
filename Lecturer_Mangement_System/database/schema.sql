-- Create the database
CREATE DATABASE IF NOT EXISTS lecturer_management;
USE lecturer_management;

-- Users table for login authentication
CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,  -- Will store hashed passwords
    email VARCHAR(100) UNIQUE,
    role ENUM('ADMIN', 'LECTURER') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Lecturers table
CREATE TABLE IF NOT EXISTS lecturers (
    lecturer_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    department VARCHAR(100),
    specialty VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Courses table
CREATE TABLE IF NOT EXISTS courses (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_code VARCHAR(20) NOT NULL UNIQUE,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    credits INT NOT NULL DEFAULT 3,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Lecturer-Course assignments (many-to-many relationship)
CREATE TABLE IF NOT EXISTS lecturer_courses (
    lecturer_id INT,
    course_id INT,
    semester VARCHAR(20) NOT NULL,  -- e.g., 'FALL_2025'
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (lecturer_id, course_id, semester),
    FOREIGN KEY (lecturer_id) REFERENCES lecturers(lecturer_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

-- Insert sample admin user (password: admin123)
INSERT INTO users (username, password, email, role) VALUES 
('admin', 'admin123', 'admin@university.edu', 'ADMIN');

-- Insert sample lecturer
INSERT INTO lecturers (first_name, last_name, email, phone, department, specialty) VALUES
('John', 'Doe', 'john.doe@university.edu', '123-456-7890', 'Computer Science', 'Software Engineering');

-- Insert sample course
INSERT INTO courses (course_code, title, description, credits) VALUES
('CS101', 'Introduction to Programming', 'Basic programming concepts using Java', 3);

-- Assign the sample course to the sample lecturer
INSERT INTO lecturer_courses (lecturer_id, course_id, semester) VALUES
(1, 1, 'FALL_2025');