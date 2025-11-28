package com.vit.assingement02_cnt_backned.dto;

import com.vit.assingement02_cnt_backned.entity.Student;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ResultResponse {
    
    private Integer resultId;
    private String studentId;
    private String subjectId;
    private String subjectCode;
    private String subjectName;
    private BigDecimal mseMarks;
    private BigDecimal eseMarks;
    private BigDecimal totalMarks;
    private String grade;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public ResultResponse() {}
    
    public ResultResponse(Integer resultId, String studentId, String subjectId, String subjectCode, 
                         String subjectName, BigDecimal mseMarks, BigDecimal eseMarks, 
                         BigDecimal totalMarks, String grade, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.resultId = resultId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.mseMarks = mseMarks;
        this.eseMarks = eseMarks;
        this.totalMarks = totalMarks;
        this.grade = grade;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
