-- =====================================================
-- 1. CREATE DATABASE
-- =====================================================
-- This creates a new database to store all project data.
CREATE DATABASE LecturerManagement;

-- Switch into the database so all further commands apply here.
USE LecturerManagement;


-- =====================================================
-- 2. FACULTY TABLE
-- =====================================================
-- Each faculty is like a department (e.g., Engineering, Science).
-- One faculty can have many lecturers.
CREATE TABLE Faculty (
    faculty_id INT AUTO_INCREMENT PRIMARY KEY,  -- unique ID
    name       VARCHAR(100) NOT NULL            -- faculty name
);


-- =====================================================
-- 3. LECTURER TABLE
-- =====================================================
-- Stores information about lecturers.
-- Each lecturer belongs to ONE faculty (via faculty_id).
CREATE TABLE Lecturer (
    lecturer_id INT AUTO_INCREMENT PRIMARY KEY, -- unique ID
    name        VARCHAR(100) NOT NULL,          -- lecturer's name
    office      VARCHAR(50),                    -- lecturer's office
    faculty_id  INT,                            -- foreign key to Faculty
    FOREIGN KEY (faculty_id) REFERENCES Faculty(faculty_id)
        ON DELETE SET NULL                      -- if faculty is deleted, lecturer’s faculty_id becomes NULL
        ON UPDATE CASCADE                       -- if faculty_id changes, update here too
);


-- =====================================================
-- 4. COURSE TABLE
-- =====================================================
-- Courses can be taught by one or more lecturers.
CREATE TABLE Course (
    course_id   INT AUTO_INCREMENT PRIMARY KEY, -- unique ID
    name        VARCHAR(100) NOT NULL,          -- course name
    code        VARCHAR(20) UNIQUE NOT NULL     -- unique course code (e.g., CS201)
);


-- =====================================================
-- 5. LECTURER-COURSE JUNCTION TABLE
-- =====================================================
-- Resolves the MANY-TO-MANY relationship:
-- - A lecturer can teach multiple courses.
-- - A course can be taught by multiple lecturers.
CREATE TABLE LecturerCourse (
    lecturer_id INT,
    course_id   INT,
    PRIMARY KEY (lecturer_id, course_id),       -- prevents duplicates
    FOREIGN KEY (lecturer_id) REFERENCES Lecturer(lecturer_id)
        ON DELETE CASCADE ON UPDATE CASCADE,    -- if lecturer is deleted, their course links are removed
    FOREIGN KEY (course_id) REFERENCES Course(course_id)
        ON DELETE CASCADE ON UPDATE CASCADE     -- if course is deleted, related links are removed
);

