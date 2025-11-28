package com.vit.assingement02_cnt_backned.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vit.assingement02_cnt_backned.config.BranchConverter;
import com.vit.assingement02_cnt_backned.config.SemesterConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {
    
    @Id
    @Column(name = "subject_id", length = 10)
    private String subjectId;
    
    @Column(name = "subject_code", unique = true, nullable = false)
    @NotBlank(message = "Subject code is required")
    private String subjectCode;
    
    @Column(name = "subject_name", nullable = false)
    @NotBlank(message = "Subject name is required")
    private String subjectName;
    
    @Convert(converter = BranchConverter.class)
    @Column(name = "branch", nullable = false)
    @NotNull(message = "Branch is required")
    private Student.Branch branch;
    
    @Convert(converter = SemesterConverter.class)
    @Column(name = "semester", nullable = false)
    @NotNull(message = "Semester is required")
    private Student.Semester semester;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Result> results;
    
    // Constructors
    public Subject() {}
    
    public Subject(String subjectCode, String subjectName, Student.Branch branch, Student.Semester semester) {
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
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public List<Result> getResults() {
        return results;
    }
    
    public void setResults(List<Result> results) {
        this.results = results;
    }
}