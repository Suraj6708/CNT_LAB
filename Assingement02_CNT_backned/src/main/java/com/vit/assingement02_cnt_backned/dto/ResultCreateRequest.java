package com.vit.assingement02_cnt_backned.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ResultCreateRequest {
    
    @NotBlank(message = "Student roll number is required")
    private String rollNo;
    
    @NotBlank(message = "Subject code is required")
    private String subjectCode;
    
    @NotNull(message = "MSE marks are required")
    @DecimalMin(value = "0.0", message = "MSE marks cannot be negative")
    @DecimalMax(value = "30.0", message = "MSE marks cannot exceed 30")
    private BigDecimal mseMarks;
    
    @NotNull(message = "ESE marks are required")
    @DecimalMin(value = "0.0", message = "ESE marks cannot be negative")
    @DecimalMax(value = "70.0", message = "ESE marks cannot exceed 70")
    private BigDecimal eseMarks;
    
    // Constructors
    public ResultCreateRequest() {}
    
    public ResultCreateRequest(String rollNo, String subjectCode, BigDecimal mseMarks, BigDecimal eseMarks) {
        this.rollNo = rollNo;
        this.subjectCode = subjectCode;
        this.mseMarks = mseMarks;
        this.eseMarks = eseMarks;
    }
    
    // Getters and Setters
    public String getRollNo() {
        return rollNo;
    }
    
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
    
    public String getSubjectCode() {
        return subjectCode;
    }
    
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
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
}