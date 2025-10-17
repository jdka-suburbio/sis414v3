package com.example.demo2.controllers;

import com.example.demo2.models.Subject;
import com.example.demo2.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subject")
@RestController
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    List<Subject> getSubjects(){
        return subjectRepository.findAll();
    }

    @PostMapping
    Subject postSubject(@RequestBody Subject subject){
        return subjectRepository.save(subject);
    }
}
