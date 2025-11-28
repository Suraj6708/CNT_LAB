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
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;
    
    @Column(name = "roll_no", unique = true, nullable = false)
    @NotBlank(message = "Roll number is required")
    private String rollNo;
    
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is required")
    private String lastName;
    
    @Convert(converter = BranchConverter.class)
    @Column(name = "branch", nullable = false)
    @NotNull(message = "Branch is required")
    private Branch branch;
    
    @Convert(converter = SemesterConverter.class)
    @Column(name = "semester", nullable = false)
    @NotNull(message = "Semester is required")
    private Semester semester;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Result> results;
    
    // Constructors
    public Student() {}
    
    public Student(String rollNo, String firstName, String lastName, Branch branch, Semester semester) {
        this.rollNo = rollNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.branch = branch;
        this.semester = semester;
    }
    
    // Getters and Setters
    public Integer getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    
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
    
    public Branch getBranch() {
        return branch;
    }
    
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    
    public Semester getSemester() {
        return semester;
    }
    
    public void setSemester(Semester semester) {
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
    
    // Enums
    public enum Branch {
        COMPUTER_SCIENCE("Computer Science"),
        INFORMATION_TECHNOLOGY("Information Technology"),
        CSE_AI("CSE(AI)"),
        ELECTRONICS("Electronics"),
        MECHANICAL("Mechanical"),
        CIVIL("Civil");
        
        private final String displayName;
        
        Branch(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    public enum Semester {
        FIRST("1"),
        SECOND("2"),
        THIRD("3"),
        FOURTH("4"),
        FIFTH("5"),
        SIXTH("6"),
        SEVENTH("7"),
        EIGHTH("8");
        
        private final String value;
        
        Semester(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }
}