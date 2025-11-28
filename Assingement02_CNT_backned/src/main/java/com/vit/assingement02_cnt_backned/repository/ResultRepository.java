package com.vit.assingement02_cnt_backned.repository;

import com.vit.assingement02_cnt_backned.entity.Result;
import com.vit.assingement02_cnt_backned.entity.Student;
import com.vit.assingement02_cnt_backned.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {

    // Find results by student entity
    List<Result> findByStudent(Student student);

    // Find results by student ID (roll number)
    List<Result> findByStudentId(String studentId);

    // Find results by subject entity
    List<Result> findBySubject(Subject subject);

    // Find results by subject ID
    List<Result> findBySubjectSubjectId(String subjectId);

    // Find specific result by student and subject entities
    Optional<Result> findByStudentAndSubject(Student student, Subject subject);

    // Find specific result by student ID and subject ID
    Optional<Result> findByStudentIdAndSubjectSubjectId(String studentId, String subjectId);

    // Custom query to find results by student roll number
    @Query("SELECT r FROM Result r WHERE r.studentId = :rollNo")
    List<Result> findByStudentRollNo(@Param("rollNo") String rollNo);

    // Custom query to find results by subject code
    @Query("SELECT r FROM Result r WHERE r.subject.subjectCode = :subjectCode")
    List<Result> findBySubjectCode(@Param("subjectCode") String subjectCode);

    // Custom query to find results by branch and semester combination
    @Query("SELECT r FROM Result r JOIN r.student s WHERE s.branch = :branch AND s.semester = :semester")
    List<Result> findByBranchAndSemester(@Param("branch") Student.Branch branch, @Param("semester") Student.Semester semester);

    // Check if result exists for student and subject combination
    boolean existsByStudentAndSubject(Student student, Subject subject);

    // Additional useful queries

    // Get all results for students in a specific branch
    @Query("SELECT r FROM Result r JOIN r.student s WHERE s.branch = :branch")
    List<Result> findByBranch(@Param("branch") Student.Branch branch);

    // Get all results for students in a specific semester
    @Query("SELECT r FROM Result r JOIN r.student s WHERE s.semester = :semester")
    List<Result> findBySemester(@Param("semester") Student.Semester semester);

    // Get results with total marks greater than specified value
    @Query("SELECT r FROM Result r WHERE r.totalMarks > :minMarks")
    List<Result> findByTotalMarksGreaterThan(@Param("minMarks") Double minMarks);

    // Get results with specific grade
    @Query("SELECT r FROM Result r WHERE r.grade = :grade")
    List<Result> findByGrade(@Param("grade") String grade);

    // Get failing students (grade = 'F')
    @Query("SELECT r FROM Result r WHERE r.grade = 'F'")
    List<Result> findFailingResults();

    // Get top performing students (grade = 'A+' or 'A')
    @Query("SELECT r FROM Result r WHERE r.grade IN ('A+', 'A')")
    List<Result> findTopPerformingResults();
}