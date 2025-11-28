package com.vit.assingement02_cnt_backned.service;

import com.vit.assingement02_cnt_backned.dto.ResultCreateRequest;
import com.vit.assingement02_cnt_backned.dto.ResultResponse;
import com.vit.assingement02_cnt_backned.dto.StudentResultResponse;
import com.vit.assingement02_cnt_backned.entity.Result;
import com.vit.assingement02_cnt_backned.entity.Student;
import com.vit.assingement02_cnt_backned.entity.Subject;
import com.vit.assingement02_cnt_backned.repository.ResultRepository;
import com.vit.assingement02_cnt_backned.repository.StudentRepository;
import com.vit.assingement02_cnt_backned.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    // Helper method to convert Result entity to ResultResponse DTO
    private ResultResponse convertToResultResponse(Result result) {
        Subject subject = result.getSubject();
        return new ResultResponse(
            result.getResultId(),
            result.getStudentId(),
            subject != null ? subject.getSubjectId() : null,
            subject != null ? subject.getSubjectCode() : null,
            subject != null ? subject.getSubjectName() : null,
            result.getMseMarks(),
            result.getEseMarks(),
            result.getTotalMarks(),
            result.getGrade(),
            result.getCreatedAt(),
            result.getUpdatedAt()
        );
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public List<ResultResponse> getAllResultsAsDTO() {
        return resultRepository.findAll().stream()
                .map(this::convertToResultResponse)
                .collect(Collectors.toList());
    }

    public Optional<Result> getResultById(Integer id) {
        return resultRepository.findById(id);
    }

    public Optional<ResultResponse> getResultByIdAsDTO(Integer id) {
        return resultRepository.findById(id)
                .map(this::convertToResultResponse);
    }

    public List<Result> getResultsByStudentId(String studentId) {
        return resultRepository.findByStudentId(studentId);
    }

    // NEW METHOD: Get comprehensive student result data for frontend
    public List<StudentResultResponse> getStudentResultsForFrontend(String rollNo) {
        List<Result> results = resultRepository.findByStudentId(rollNo);
        
        return results.stream().map(result -> {
            Student student = result.getStudent();
            Subject subject = result.getSubject();
            
            return new StudentResultResponse(
                result.getResultId(),
                result.getStudentId(),
                student != null ? student.getRollNo() : result.getStudentId(),
                student != null ? student.getFirstName() : null,
                student != null ? student.getLastName() : null,
                student != null ? student.getBranch() : null,
                student != null ? student.getSemester() : null,
                subject != null ? subject.getSubjectId() : null,
                subject != null ? subject.getSubjectCode() : null,
                subject != null ? subject.getSubjectName() : null,
                result.getMseMarks(),
                result.getEseMarks(),
                result.getTotalMarks(),
                result.getGrade(),
                result.getCreatedAt(),
                result.getUpdatedAt()
            );
        }).collect(Collectors.toList());
    }

    public List<Result> getResultsBySubjectId(String subjectId) {
        return resultRepository.findBySubjectSubjectId(subjectId);
    }

    public List<Result> getResultsByStudentRollNo(String rollNo) {
        return resultRepository.findByStudentRollNo(rollNo);
    }

    public List<Result> getResultsBySubjectCode(String subjectCode) {
        return resultRepository.findBySubjectCode(subjectCode);
    }

    public List<Result> getResultsByBranchAndSemester(Student.Branch branch, Student.Semester semester) {
        return resultRepository.findByBranchAndSemester(branch, semester);
    }

    public Optional<Result> getResultByStudentAndSubject(String studentId, String subjectId) {
        return resultRepository.findByStudentIdAndSubjectSubjectId(studentId, subjectId);
    }

    // NEW METHOD: Create result using roll number and subject code
    public Result createResultByRollNoAndSubjectCode(ResultCreateRequest request) {
        // Find student by roll number
        Student student = studentRepository.findByRollNo(request.getRollNo())
                .orElseThrow(() -> new RuntimeException("Student not found with roll number: " + request.getRollNo()));

        // Find subject by subject code
        Subject subject = subjectRepository.findBySubjectCode(request.getSubjectCode())
                .orElseThrow(() -> new RuntimeException("Subject not found with code: " + request.getSubjectCode()));

        // Check if result already exists for this student-subject combination
        if (resultRepository.findByStudentIdAndSubjectSubjectId(request.getRollNo(), subject.getSubjectId()).isPresent()) {
            throw new RuntimeException("Result already exists for student " + request.getRollNo() +
                    " and subject " + request.getSubjectCode());
        }

        // Create new result
        Result result = new Result();
        result.setStudentId(request.getRollNo());
        result.setSubject(subject);
        result.setMseMarks(request.getMseMarks());
        result.setEseMarks(request.getEseMarks());

        return resultRepository.save(result);
    }

    // NEW METHOD: Create result using roll number and subject code (returns DTO)
    public ResultResponse createResultByRollNoAndSubjectCodeAsDTO(ResultCreateRequest request) {
        Result result = createResultByRollNoAndSubjectCode(request);
        return convertToResultResponse(result);
    }

    // UPDATED METHOD: Update result using roll number and subject code
    public Result updateResultByRollNoAndSubjectCode(String rollNo, String subjectCode, ResultCreateRequest request) {
        // Find student by roll number
        Student student = studentRepository.findByRollNo(rollNo)
                .orElseThrow(() -> new RuntimeException("Student not found with roll number: " + rollNo));

        // Find subject by subject code
        Subject subject = subjectRepository.findBySubjectCode(subjectCode)
                .orElseThrow(() -> new RuntimeException("Subject not found with code: " + subjectCode));

        // Find existing result
        Result result = resultRepository.findByStudentIdAndSubjectSubjectId(rollNo, subject.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Result not found for student " + rollNo +
                        " and subject " + subjectCode));

        // Update marks
        result.setMseMarks(request.getMseMarks());
        result.setEseMarks(request.getEseMarks());

        return resultRepository.save(result);
    }

    // NEW METHOD: Update result using roll number and subject code (returns DTO)
    public ResultResponse updateResultByRollNoAndSubjectCodeAsDTO(String rollNo, String subjectCode, ResultCreateRequest request) {
        Result result = updateResultByRollNoAndSubjectCode(rollNo, subjectCode, request);
        return convertToResultResponse(result);
    }

    // EXISTING METHODS (keeping for backward compatibility)
    public Result createResult(Result result) {
        // Verify student exists by roll number
        Student student = studentRepository.findByRollNo(result.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Verify subject exists
        Subject subject = subjectRepository.findById(result.getSubject().getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        // Check if result already exists for this student-subject combination
        if (resultRepository.findByStudentIdAndSubjectSubjectId(result.getStudentId(), result.getSubject().getSubjectId()).isPresent()) {
            throw new RuntimeException("Result already exists for this student and subject combination");
        }

        result.setSubject(subject);
        return resultRepository.save(result);
    }

    public Result updateResult(Integer id, Result resultDetails) {
        return resultRepository.findById(id)
                .map(result -> {
                    result.setMseMarks(resultDetails.getMseMarks());
                    result.setEseMarks(resultDetails.getEseMarks());
                    return resultRepository.save(result);
                })
                .orElseThrow(() -> new RuntimeException("Result not found with id: " + id));
    }

    public void deleteResult(Integer id) {
        if (!resultRepository.existsById(id)) {
            throw new RuntimeException("Result not found with id: " + id);
        }
        resultRepository.deleteById(id);
    }

    // NEW METHOD: Delete result by roll number and subject code
    public void deleteResultByRollNoAndSubjectCode(String rollNo, String subjectCode) {
        // Find student by roll number
        Student student = studentRepository.findByRollNo(rollNo)
                .orElseThrow(() -> new RuntimeException("Student not found with roll number: " + rollNo));

        // Find subject by subject code
        Subject subject = subjectRepository.findBySubjectCode(subjectCode)
                .orElseThrow(() -> new RuntimeException("Subject not found with code: " + subjectCode));

        // Find and delete result
        Result result = resultRepository.findByStudentIdAndSubjectSubjectId(rollNo, subject.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Result not found for student " + rollNo +
                        " and subject " + subjectCode));

        resultRepository.delete(result);
    }
}