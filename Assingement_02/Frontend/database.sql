-- Student Management System Database Schema
-- Created for Frontend Application

-- Create the database
CREATE DATABASE IF NOT EXISTS student_management_system;
USE student_management_system;

-- Drop tables if they exist (for clean setup)
DROP TABLE IF EXISTS results;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS subjects;

-- Create students table
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    roll_no VARCHAR(20) UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    branch ENUM('Computer Science', 'Information Technology', 'CSE(AI)', 'Electronics', 'Mechanical', 'Civil') NOT NULL,
    semester ENUM('1', '2', '3', '4', '5', '6', '7', '8') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create subjects table
CREATE TABLE subjects (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_code VARCHAR(10) UNIQUE NOT NULL,
    subject_name VARCHAR(100) NOT NULL,
    branch ENUM('Computer Science', 'Information Technology', 'CSE(AI)', 'Electronics', 'Mechanical', 'Civil') NOT NULL,
    semester ENUM('1', '2', '3', '4', '5', '6', '7', '8') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create results table
CREATE TABLE results (
    result_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    mse_marks DECIMAL(5,2) NOT NULL CHECK (mse_marks >= 0 AND mse_marks <= 30),
    ese_marks DECIMAL(5,2) NOT NULL CHECK (ese_marks >= 0 AND ese_marks <= 70),
    total_marks DECIMAL(5,2) GENERATED ALWAYS AS (mse_marks + ese_marks) STORED,
    grade VARCHAR(2) GENERATED ALWAYS AS (
        CASE 
            WHEN (mse_marks + ese_marks) >= 90 THEN 'A+'
            WHEN (mse_marks + ese_marks) >= 80 THEN 'A'
            WHEN (mse_marks + ese_marks) >= 70 THEN 'B+'
            WHEN (mse_marks + ese_marks) >= 60 THEN 'B'
            WHEN (mse_marks + ese_marks) >= 50 THEN 'C+'
            WHEN (mse_marks + ese_marks) >= 40 THEN 'C'
            WHEN (mse_marks + ese_marks) >= 35 THEN 'D'
            ELSE 'F'
        END
    ) STORED,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subjects(subject_id) ON DELETE CASCADE,
    UNIQUE KEY unique_student_subject (student_id, subject_id)
);

-- Create indexes for better performance
CREATE INDEX idx_students_roll_no ON students(roll_no);
CREATE INDEX idx_students_branch ON students(branch);
CREATE INDEX idx_students_semester ON students(semester);
CREATE INDEX idx_subjects_branch ON subjects(branch);
CREATE INDEX idx_subjects_semester ON subjects(semester);
CREATE INDEX idx_results_student_id ON results(student_id);
CREATE INDEX idx_results_subject_id ON results(subject_id);
