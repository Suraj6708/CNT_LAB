package com.vit.assingement02_cnt_backned.config;

import com.vit.assingement02_cnt_backned.entity.Student;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BranchConverter implements AttributeConverter<Student.Branch, String> {

    @Override
    public String convertToDatabaseColumn(Student.Branch branch) {
        if (branch == null) {
            return null;
        }
        return branch.name(); // Store as enum constant (e.g., "COMPUTER_SCIENCE")
    }

    @Override
    public Student.Branch convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        
        // First try to match by enum constant name
        try {
            return Student.Branch.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            // If that fails, try to match by display name
            for (Student.Branch branch : Student.Branch.values()) {
                if (branch.getDisplayName().equals(dbData)) {
                    return branch;
                }
            }
            // If still not found, throw a more descriptive exception
            throw new IllegalArgumentException("No enum constant found for Branch: " + dbData + 
                ". Valid values are: " + java.util.Arrays.toString(Student.Branch.values()));
        }
    }
}
