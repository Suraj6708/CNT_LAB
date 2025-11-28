package com.vit.assingement02_cnt_backned.service;

import com.vit.assingement02_cnt_backned.dto.SubjectCreateRequest;
import com.vit.assingement02_cnt_backned.entity.Subject;
import com.vit.assingement02_cnt_backned.entity.Student;
import com.vit.assingement02_cnt_backned.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
    
    public Optional<Subject> getSubjectById(String id) {
        return subjectRepository.findById(id);
    }
    
    public Optional<Subject> getSubjectByCode(String subjectCode) {
        return subjectRepository.findBySubjectCode(subjectCode);
    }
    
    public List<Subject> getSubjectsByBranch(Student.Branch branch) {
        return subjectRepository.findByBranch(branch);
    }
    
    public List<Subject> getSubjectsBySemester(Student.Semester semester) {
        return subjectRepository.findBySemester(semester);
    }
    
    public List<Subject> getSubjectsByBranchAndSemester(Student.Branch branch, Student.Semester semester) {
        return subjectRepository.findByBranchAndSemester(branch, semester);
    }
    
    public List<Subject> searchSubjectsByName(String subjectName) {
        return subjectRepository.findBySubjectNameContaining(subjectName);
    }
    
    public Subject createSubject(Subject subject) {
        if (subjectRepository.existsBySubjectCode(subject.getSubjectCode())) {
            throw new RuntimeException("Subject with code " + subject.getSubjectCode() + " already exists");
        }
        return subjectRepository.save(subject);
    }
    
    public Subject updateSubject(String id, Subject subjectDetails) {
        return subjectRepository.findById(id)
            .map(subject -> {
                if (!subject.getSubjectCode().equals(subjectDetails.getSubjectCode()) && 
                    subjectRepository.existsBySubjectCode(subjectDetails.getSubjectCode())) {
                    throw new RuntimeException("Subject with code " + subjectDetails.getSubjectCode() + " already exists");
                }
                subject.setSubjectCode(subjectDetails.getSubjectCode());
                subject.setSubjectName(subjectDetails.getSubjectName());
                subject.setBranch(subjectDetails.getBranch());
                subject.setSemester(subjectDetails.getSemester());
                return subjectRepository.save(subject);
            })
            .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
    }
    
    public Subject createSubjectFromRequest(SubjectCreateRequest request) {
        Subject subject = new Subject();
        subject.setSubjectId(request.getSubjectId());
        subject.setSubjectCode(request.getSubjectCode());
        subject.setSubjectName(request.getSubjectName());
        subject.setBranch(request.getBranch());
        subject.setSemester(request.getSemester());
        return createSubject(subject);
    }
    
    public void deleteSubject(String id) {
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Subject not found with id: " + id);
        }
        subjectRepository.deleteById(id);
    }
}