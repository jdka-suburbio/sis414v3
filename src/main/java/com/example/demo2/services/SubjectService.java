package com.example.demo2.services;

import com.example.demo2.models.Subject;
import com.example.demo2.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getSubject(){
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    public Subject createSubject(Subject subject){
        subjectRepository.save(subject);
        return subject;
    }

    public Subject updateSubject(Long id , Subject subject){
        if(!subjectRepository.existsById(id))
            throw new RuntimeException(" Subject not found with this id");
        subject.setId(id);
        return subjectRepository.save(subject);
    }

    public Optional<Subject> partiallyUpdateSubject(Long id, Subject subject) {
        return subjectRepository.findById(id).map(item -> {
            if (subject.getCode() != null) item.setCode(subject.getCode());
            if (subject.getName() != null) item.setName(subject.getName());
            if (subject.getTheoryHours() > 0) item.setTheoryHours(subject.getTheoryHours());
            return subjectRepository.save(item);
        });
    }

    public boolean deleteSubject(Long id){
        if(!subjectRepository.existsById(id)){
            return false;
        }
        subjectRepository.deleteById(id);
        return true;
    }
}
