package com.example.demo2.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.demo2.models.Student;
import com.example.demo2.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/student")
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://jdka-suburbio.github.io"})
@Tag(name = "Student", description = "This endpoint permits create, read, update and delete operations for students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    @Operation(
            summary = "Get all students",
            tags = {"Student"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of students retrieved successfully")
            }
    )
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a student by ID",
            tags = {"Student"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student found"),
                    @ApiResponse(responseCode = "404", description = "Student not found")
            }
    )
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Create a new student",
            tags = {"Student"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Student created successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        try {
            if (student == null) {
                return ResponseEntity.badRequest().body("Error: Datos inválidos");
            }
            Student createdStudent = studentService.createStudent(student);
            if (createdStudent == null || createdStudent.getId() == null) {
                return ResponseEntity.badRequest().body("Error: No se pudo crear el estudiante");
            }
            return ResponseEntity.status(201).body("Estudiante agregado con id " + createdStudent.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a student",
            tags = {"Student"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Student not found")
            }
    )
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a student",
            tags = {"Student"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Student not found")
            }
    )
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        if (studentService.deleteStudent(id)) {
            return ResponseEntity.ok("Student deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/code/{studentCode}")
    @Operation(
            summary = "Get student by student code",
            tags = {"Student"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student found"),
                    @ApiResponse(responseCode = "404", description = "Student not found")
            }
    )
    public ResponseEntity<Student> getByStudentCode(@PathVariable String studentCode) {
        Optional<Student> student = studentService.findByStudentCode(studentCode);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/career/{careerId}")
    @Operation(
            summary = "Get students by career",
            tags = {"Student"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of students retrieved successfully")
            }
    )
    public ResponseEntity<List<Student>> getStudentsByCareer(@PathVariable Long careerId) {
        return ResponseEntity.ok(studentService.findByCareer(careerId));
    }
}
