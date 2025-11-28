package com.vit.assingement02_cnt_backned.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "results", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "subject_id"}))
public class Result {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Integer resultId;
    
    @Column(name = "student_id", nullable = false, length = 20)
    @NotNull(message = "Student roll number is required")
    private String studentId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "roll_no", insertable = false, updatable = false)
    private Student student;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    @NotNull(message = "Subject is required")
    private Subject subject;
    
    @Column(name = "mse_marks", nullable = false, precision = 5, scale = 2)
    @NotNull(message = "MSE marks are required")
    @DecimalMin(value = "0.0", message = "MSE marks cannot be negative")
    @DecimalMax(value = "30.0", message = "MSE marks cannot exceed 30")
    private BigDecimal mseMarks;
    
    @Column(name = "ese_marks", nullable = false, precision = 5, scale = 2)
    @NotNull(message = "ESE marks are required")
    @DecimalMin(value = "0.0", message = "ESE marks cannot be negative")
    @DecimalMax(value = "70.0", message = "ESE marks cannot exceed 70")
    private BigDecimal eseMarks;
    
    @Column(name = "total_marks", precision = 5, scale = 2, insertable = false, updatable = false)
    private BigDecimal totalMarks;
    
    @Column(name = "grade", insertable = false, updatable = false)
    private String grade;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public Result() {}
    
    public Result(String studentId, Subject subject, BigDecimal mseMarks, BigDecimal eseMarks) {
        this.studentId = studentId;
        this.subject = subject;
        this.mseMarks = mseMarks;
        this.eseMarks = eseMarks;
    }
    
    // Getters and Setters
    public Integer getResultId() {
        return resultId;
    }
    
    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    
    public Subject getSubject() {
        return subject;
    }
    
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public BigDecimal getMseMarks() {
        return mseMarks;
    }
    
    public void setMseMarks(BigDecimal mseMarks) {
        this.mseMarks = mseMarks;
    }
    
    public BigDecimal getEseMarks() {
        return eseMarks;
    }
    
    public void setEseMarks(BigDecimal eseMarks) {
        this.eseMarks = eseMarks;
    }
    
    public BigDecimal getTotalMarks() {
        return totalMarks;
    }
    
    public void setTotalMarks(BigDecimal totalMarks) {
        this.totalMarks = totalMarks;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
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
}