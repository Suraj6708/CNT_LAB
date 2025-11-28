package com.vit.assingement02_cnt_backned.dto;

import com.vit.assingement02_cnt_backned.entity.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentCreateRequest {
    
    @NotBlank(message = "Roll number is required")
    private String rollNo;
    
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    private String lastName;
    
    @NotNull(message = "Branch is required")
    private Student.Branch branch;
    
    @NotNull(message = "Semester is required")
    private Student.Semester semester;
    
    // Constructors
    public StudentCreateRequest() {}
    
    public StudentCreateRequest(String rollNo, String firstName, String lastName, 
                               Student.Branch branch, Student.Semester semester) {
        this.rollNo = rollNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.branch = branch;
        this.semester = semester;
    }
    
    // Getters and Setters
    public String getRollNo() {
        return rollNo;
    }
    
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Student.Branch getBranch() {
        return branch;
    }
    
    public void setBranch(Student.Branch branch) {
        this.branch = branch;
    }
    
    public Student.Semester getSemester() {
        return semester;
    }
    
    public void setSemester(Student.Semester semester) {
        this.semester = semester;
    }
}
