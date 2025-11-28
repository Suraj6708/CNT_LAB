package com.vit.assingement02_cnt_backned.service;

import com.vit.assingement02_cnt_backned.dto.StudentCreateRequest;
import com.vit.assingement02_cnt_backned.entity.Student;
import com.vit.assingement02_cnt_backned.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }
    
    public Optional<Student> getStudentByRollNo(String rollNo) {
        return studentRepository.findByRollNo(rollNo);
    }
    
    public List<Student> getStudentsByBranch(Student.Branch branch) {
        return studentRepository.findByBranch(branch);
    }
    
    public List<Student> getStudentsBySemester(Student.Semester semester) {
        return studentRepository.findBySemester(semester);
    }
    
    public List<Student> getStudentsByBranchAndSemester(Student.Branch branch, Student.Semester semester) {
        return studentRepository.findByBranchAndSemester(branch, semester);
    }
    
    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContaining(name);
    }
    
    public Student createStudent(Student student) {
        System.out.println(student);
        if (studentRepository.existsByRollNo(student.getRollNo())) {
            throw new RuntimeException("Student with roll number " + student.getRollNo() + " already exists");
        }
        return studentRepository.save(student);
    }
    
    public Student updateStudent(Integer id, Student studentDetails) {
        return studentRepository.findById(id)
            .map(student -> {
                if (!student.getRollNo().equals(studentDetails.getRollNo()) && 
                    studentRepository.existsByRollNo(studentDetails.getRollNo())) {
                    throw new RuntimeException("Student with roll number " + studentDetails.getRollNo() + " already exists");
                }
                student.setRollNo(studentDetails.getRollNo());
                student.setFirstName(studentDetails.getFirstName());
                student.setLastName(studentDetails.getLastName());
                student.setBranch(studentDetails.getBranch());
                student.setSemester(studentDetails.getSemester());
                return studentRepository.save(student);
            })
            .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }
    
    public Student createStudentFromRequest(StudentCreateRequest request) {
        Student student = new Student();
        student.setRollNo(request.getRollNo());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setBranch(request.getBranch());
        student.setSemester(request.getSemester());
        return createStudent(student);
    }
    
    public void deleteStudent(Integer id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}