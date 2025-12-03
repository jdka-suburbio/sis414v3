package com.example.demo2.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.demo2.models.Subject;
import com.example.demo2.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/subject")
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://jdka-suburbio.github.io"})
@Tag(name = "Subject", description = "This endpoint permits create, read, update and delete operations for subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    @Operation(
            summary = "Get all subjects",
            tags = {"Subject"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of subjects retrieved successfully")
            }
    )
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a subject by ID",
            tags = {"Subject"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Subject found"),
                    @ApiResponse(responseCode = "404", description = "Subject not found")
            }
    )
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        Optional<Subject> subject = subjectService.getSubjectById(id);
        return subject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Create a new subject",
            tags = {"Subject"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Subject created successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public ResponseEntity<String> createSubject(@RequestBody Subject subject) {
        try {
            if (subject == null) {
                return ResponseEntity.badRequest().body("Error: Datos inválidos");
            }
            Subject createdSubject = subjectService.createSubject(subject);
            if (createdSubject == null || createdSubject.getId() == null) {
                return ResponseEntity.badRequest().body("Error: No se pudo crear la materia");
            }
            return ResponseEntity.status(201).body("Materia agregada con id " + createdSubject.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a subject",
            tags = {"Subject"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Subject updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Subject not found")
            }
    )
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @RequestBody Subject subject) {
        Subject updatedSubject = subjectService.updateSubject(id, subject);
        if (updatedSubject == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedSubject);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a subject",
            tags = {"Subject"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Subject deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Subject not found")
            }
    )
    public ResponseEntity<String> deleteSubject(@PathVariable Long id) {
        if (subjectService.deleteSubject(id)) {
            return ResponseEntity.ok("Subject deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/code/{code}")
    @Operation(
            summary = "Get subject by code",
            tags = {"Subject"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Subject found"),
                    @ApiResponse(responseCode = "404", description = "Subject not found")
            }
    )
    public ResponseEntity<Subject> getByCode(@PathVariable String code) {
        Optional<Subject> subject = subjectService.findByCode(code);
        return subject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/career/{careerId}")
    @Operation(
            summary = "Get subjects by career",
            tags = {"Subject"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of subjects retrieved successfully")
            }
    )
    public ResponseEntity<List<Subject>> getSubjectsByCareer(@PathVariable Long careerId) {
        return ResponseEntity.ok(subjectService.findByCareer(careerId));
    }
}
