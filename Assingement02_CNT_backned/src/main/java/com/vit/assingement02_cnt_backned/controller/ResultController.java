package com.vit.assingement02_cnt_backned.controller;

import com.vit.assingement02_cnt_backned.dto.ResultCreateRequest;
import com.vit.assingement02_cnt_backned.dto.ResultResponse;
import com.vit.assingement02_cnt_backned.dto.StudentResultResponse;
import com.vit.assingement02_cnt_backned.entity.Result;
import com.vit.assingement02_cnt_backned.entity.Student;
import com.vit.assingement02_cnt_backned.service.ResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
@CrossOrigin(origins = "http://localhost:5173")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping
    public ResponseEntity<List<Result>> getAllResults() {
        try {
            List<Result> results = resultService.getAllResults();
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable Integer id) {
        try {
            return resultService.getResultById(id)
                    .map(result -> ResponseEntity.ok(result))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Result>> getResultsByStudentId(@PathVariable String studentId) {
        try {
            List<Result> results = resultService.getResultsByStudentId(studentId);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // NEW ENDPOINT: Get comprehensive student results for frontend
    @GetMapping("/student/{rollNo}/details")
    public ResponseEntity<List<StudentResultResponse>> getStudentResultsForFrontend(@PathVariable String rollNo) {
        try {
            List<StudentResultResponse> results = resultService.getStudentResultsForFrontend(rollNo);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<Result>> getResultsBySubjectId(@PathVariable String subjectId) {
        try {
            List<Result> results = resultService.getResultsBySubjectId(subjectId);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/student/roll/{rollNo}")
    public ResponseEntity<List<Result>> getResultsByStudentRollNo(@PathVariable String rollNo) {
        try {
            List<Result> results = resultService.getResultsByStudentRollNo(rollNo);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/subject/code/{subjectCode}")
    public ResponseEntity<List<Result>> getResultsBySubjectCode(@PathVariable String subjectCode) {
        try {
            List<Result> results = resultService.getResultsBySubjectCode(subjectCode);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/branch/{branch}/semester/{semester}")
    public ResponseEntity<List<Result>> getResultsByBranchAndSemester(
            @PathVariable Student.Branch branch,
            @PathVariable Student.Semester semester) {
        try {
            List<Result> results = resultService.getResultsByBranchAndSemester(branch, semester);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/student/{studentId}/subject/{subjectId}")
    public ResponseEntity<Result> getResultByStudentAndSubject(
            @PathVariable String studentId,
            @PathVariable String subjectId) {
        try {
            return resultService.getResultByStudentAndSubject(studentId, subjectId)
                    .map(result -> ResponseEntity.ok(result))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // NEW ENDPOINT: Create result using roll number and subject code
    @PostMapping("/add-marks")
    public ResponseEntity<?> createResultByRollNoAndSubjectCode(@Valid @RequestBody ResultCreateRequest request) {
        try {
            ResultResponse createdResult = resultService.createResultByRollNoAndSubjectCodeAsDTO(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdResult);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating result: " + e.getMessage());
        }
    }

    // NEW ENDPOINT: Update result using roll number and subject code
    @PutMapping("/update-marks/{rollNo}/{subjectCode}")
    public ResponseEntity<?> updateResultByRollNoAndSubjectCode(
            @PathVariable String rollNo,
            @PathVariable String subjectCode,
            @Valid @RequestBody ResultCreateRequest request) {
        try {
            ResultResponse updatedResult = resultService.updateResultByRollNoAndSubjectCodeAsDTO(rollNo, subjectCode, request);
            return ResponseEntity.ok(updatedResult);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating result: " + e.getMessage());
        }
    }

    // NEW ENDPOINT: Delete result using roll number and subject code
    @DeleteMapping("/delete-marks/{rollNo}/{subjectCode}")
    public ResponseEntity<?> deleteResultByRollNoAndSubjectCode(
            @PathVariable String rollNo,
            @PathVariable String subjectCode) {
        try {
            resultService.deleteResultByRollNoAndSubjectCode(rollNo, subjectCode);
            return ResponseEntity.ok().body("Result deleted successfully for student " + rollNo +
                    " and subject " + subjectCode);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting result: " + e.getMessage());
        }
    }

    // EXISTING ENDPOINTS (keeping for backward compatibility)
    @PostMapping
    public ResponseEntity<?> createResult(@Valid @RequestBody Result result) {
        try {
            Result createdResult = resultService.createResult(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdResult);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating result: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateResult(@PathVariable Integer id, @Valid @RequestBody Result resultDetails) {
        try {
            Result updatedResult = resultService.updateResult(id, resultDetails);
            return ResponseEntity.ok(updatedResult);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating result: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResult(@PathVariable Integer id) {
        try {
            resultService.deleteResult(id);
            return ResponseEntity.ok().body("Result deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting result: " + e.getMessage());
        }
    }
}