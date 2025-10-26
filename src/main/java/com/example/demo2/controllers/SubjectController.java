
package com.example.demo2.controllers;

import com.example.demo2.models.Subject;
import com.example.demo2.repository.SubjectRepository;
import com.example.demo2.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public ResponseEntity<List<Subject>> getSubjects(){
        return ResponseEntity.ok(subjectService.getSubject());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id){
        return subjectService.getSubjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> postSubject(@RequestBody Subject subject){
        Subject saved = subjectService.createSubject(subject);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putSubject(@PathVariable Long id, @RequestBody Subject subject){
        if(!subjectRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        subject.setId(id);
        subjectRepository.save(subject);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchSubject(@PathVariable Long id, @RequestBody Subject subject){
        return subjectService.partiallyUpdateSubject(id, subject)
                .map(updated -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id){
        if(!subjectRepository.existsById(id)){
            return ResponseEntity.badRequest().build();
        }
        subjectRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
