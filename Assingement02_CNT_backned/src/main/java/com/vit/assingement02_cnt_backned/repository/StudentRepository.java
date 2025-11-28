package com.vit.assingement02_cnt_backned.repository;

import com.vit.assingement02_cnt_backned.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Find student by roll number (unique)
    Optional<Student> findByRollNo(String rollNo);

    // Find students by branch
    List<Student> findByBranch(Student.Branch branch);

    // Find students by semester
    List<Student> findBySemester(Student.Semester semester);

    // Find students by branch and semester combination
    List<Student> findByBranchAndSemester(Student.Branch branch, Student.Semester semester);

    // Custom query to search students by name (first or last name containing the search term)
    @Query("SELECT s FROM Student s WHERE s.firstName LIKE %:name% OR s.lastName LIKE %:name%")
    List<Student> findByNameContaining(@Param("name") String name);

    // Check if student exists by roll number
    boolean existsByRollNo(String rollNo);
}