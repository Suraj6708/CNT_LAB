package com.vit.assingement02_cnt_backned.repository;

import com.vit.assingement02_cnt_backned.entity.Subject;
import com.vit.assingement02_cnt_backned.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {

    // Find subject by subject code (unique)
    Optional<Subject> findBySubjectCode(String subjectCode);

    // Find subjects by branch
    List<Subject> findByBranch(Student.Branch branch);

    // Find subjects by semester
    List<Subject> findBySemester(Student.Semester semester);

    // Find subjects by branch and semester combination
    List<Subject> findByBranchAndSemester(Student.Branch branch, Student.Semester semester);

    // Search subjects by name containing the search term
    List<Subject> findBySubjectNameContaining(String subjectName);

    // Check if subject exists by subject code
    boolean existsBySubjectCode(String subjectCode);
}