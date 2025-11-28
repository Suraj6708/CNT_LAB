package com.vit.assingement02_cnt_backned.dto;

import com.vit.assingement02_cnt_backned.entity.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubjectCreateRequest {
    
    @NotBlank(message = "Subject ID is required")
    private String subjectId;
    
    @NotBlank(message = "Subject code is required")
    private String subjectCode;
    
    @NotBlank(message = "Subject name is required")
    private String subjectName;
    
    @NotNull(message = "Branch is required")
    private Student.Branch branch;
    
    @NotNull(message = "Semester is required")
    private Student.Semester semester;
    
    // Constructors
    public SubjectCreateRequest() {}
    
    public SubjectCreateRequest(String subjectId, String subjectCode, String subjectName, 
                               Student.Branch branch, Student.Semester semester) {
        this.subjectId = subjectId;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.branch = branch;
        this.semester = semester;
    }
    
    // Getters and Setters
    public String getSubjectId() {
        return subjectId;
    }
    
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
    
    public String getSubjectCode() {
        return subjectCode;
    }
    
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    
    public String getSubjectName() {
        return subjectName;
    }
    
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
