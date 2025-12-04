package com.MirnaCalla.practica6.controllers;

import com.MirnaCalla.practica6.models.Subject;
import com.MirnaCalla.practica6.repository.SubjectRepository;
import com.MirnaCalla.practica6.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/subject")
@RestController
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    ResponseEntity<List<Subject>> getSubjects(){
        return ResponseEntity.ok(subjectService.getSubject());
    }

    @GetMapping("/{id}")
    ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<?> postSubject(@RequestBody Subject subject){
        Subject saved = subjectService.creacteSubject(subject);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(subject.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> putSubject(@PathVariable Long id, @RequestBody Subject subject) {
        Subject updated = subjectService.updateSubject(id, subject);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchSubject(@PathVariable Long id, @RequestBody Subject subject) {
        return subjectService.partiallyUpdateSubject(id, subject)
                .map(updated -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id){
        if(subjectService.deleteSubject(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }



}
