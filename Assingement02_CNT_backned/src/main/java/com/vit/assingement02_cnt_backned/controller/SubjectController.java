package com.vit.assingement02_cnt_backned.controller;

import com.vit.assingement02_cnt_backned.dto.SubjectCreateRequest;
import com.vit.assingement02_cnt_backned.entity.Subject;
import com.vit.assingement02_cnt_backned.entity.Student;
import com.vit.assingement02_cnt_backned.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@CrossOrigin(origins = "http://localhost:5173")
public class SubjectController {
    
    @Autowired
    private SubjectService subjectService;
    
    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        try {
            List<Subject> subjects = subjectService.getAllSubjects();
            return ResponseEntity.ok(subjects);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable String id) {
        try {
            return subjectService.getSubjectById(id)
                .map(subject -> ResponseEntity.ok(subject))
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/code/{subjectCode}")
    public ResponseEntity<Subject> getSubjectByCode(@PathVariable String subjectCode) {
        try {
            return subjectService.getSubjectByCode(subjectCode)
                .map(subject -> ResponseEntity.ok(subject))
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/branch/{branch}")
    public ResponseEntity<List<Subject>> getSubjectsByBranch(@PathVariable Student.Branch branch) {
        try {
            List<Subject> subjects = subjectService.getSubjectsByBranch(branch);
            return ResponseEntity.ok(subjects);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/semester/{semester}")
    public ResponseEntity<List<Subject>> getSubjectsBySemester(@PathVariable Student.Semester semester) {
        try {
            List<Subject> subjects = subjectService.getSubjectsBySemester(semester);
            return ResponseEntity.ok(subjects);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/branch/{branch}/semester/{semester}")
    public ResponseEntity<List<Subject>> getSubjectsByBranchAndSemester(
            @PathVariable Student.Branch branch, 
            @PathVariable Student.Semester semester) {
        try {
            List<Subject> subjects = subjectService.getSubjectsByBranchAndSemester(branch, semester);
            return ResponseEntity.ok(subjects);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Subject>> searchSubjectsByName(@RequestParam String name) {
        try {
            List<Subject> subjects = subjectService.searchSubjectsByName(name);
            return ResponseEntity.ok(subjects);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> createSubjectFromRequest(@Valid @RequestBody SubjectCreateRequest request) {
        try {
            Subject createdSubject = subjectService.createSubjectFromRequest(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSubject);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error creating subject: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable String id, @Valid @RequestBody Subject subjectDetails) {
        try {
            Subject updatedSubject = subjectService.updateSubject(id, subjectDetails);
            return ResponseEntity.ok(updatedSubject);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error updating subject: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable String id) {
        try {
            subjectService.deleteSubject(id);
            return ResponseEntity.ok().body("Subject deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error deleting subject: " + e.getMessage());
        }
    }
}