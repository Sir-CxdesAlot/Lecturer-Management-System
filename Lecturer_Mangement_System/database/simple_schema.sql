-- Create the database
CREATE DATABASE IF NOT EXISTS lecturer_management;
USE lecturer_management;

-- Simple lecturers table
CREATE TABLE lecturers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    department VARCHAR(100)
);

-- Simple courses table
CREATE TABLE courses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20) NOT NULL UNIQUE,
    title VARCHAR(100) NOT NULL,
    description TEXT
);

-- Simple table to connect lecturers and courses
CREATE TABLE lecturer_courses (
    lecturer_id INT,
    course_id INT,
    FOREIGN KEY (lecturer_id) REFERENCES lecturers(id),
    FOREIGN KEY (course_id) REFERENCES courses(id),
    PRIMARY KEY (lecturer_id, course_id)
);

-- Insert some sample data
INSERT INTO lecturers (first_name, last_name, email, department) VALUES 
('John', 'Doe', 'john.doe@university.edu', 'Computer Science'),
('Jane', 'Smith', 'jane.smith@university.edu', 'Mathematics');

INSERT INTO courses (code, title, description) VALUES
('CS101', 'Introduction to Programming', 'Learn basic programming concepts'),
('MATH201', 'Calculus I', 'Introduction to calculus');