package com.vit.assingement02_cnt_backned.config;

import com.vit.assingement02_cnt_backned.entity.Student;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SemesterConverter implements AttributeConverter<Student.Semester, String> {

    @Override
    public String convertToDatabaseColumn(Student.Semester semester) {
        if (semester == null) {
            return null;
        }
        return semester.name(); // Store as enum constant (e.g., "FIRST")
    }

    @Override
    public Student.Semester convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        
        // First try to match by enum constant name
        try {
            return Student.Semester.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            // If that fails, try to match by value (e.g., "1", "2", etc.)
            for (Student.Semester semester : Student.Semester.values()) {
                if (semester.getValue().equals(dbData)) {
                    return semester;
                }
            }
            // If still not found, throw a more descriptive exception
            throw new IllegalArgumentException("No enum constant found for Semester: " + dbData + 
                ". Valid values are: " + java.util.Arrays.toString(Student.Semester.values()));
        }
    }
}
