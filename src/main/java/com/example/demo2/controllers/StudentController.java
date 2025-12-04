package com.example.demo2.controllers;

import com.example.demo2.models.Student;
import com.example.demo2.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/students")
@RestController
@Tag(name = "Students", description = "Operations for managing students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get students",
            description = "Returns all students or filters them by career name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Students retrieved successfully")
            }
    )
    List<Student> getStudents(@RequestParam(required = false, defaultValue = "*") String nameCareer) {
        if(nameCareer.equals("*"))
            return this.studentService.students;

        return this.studentService.students.stream()
                .filter(s -> s.getCareer().getName().equals(nameCareer))
                .toList();
    }

    @GetMapping("/{ru}")
    @Operation(
            summary = "Get a student by RU",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student found"),
                    @ApiResponse(responseCode = "400", description = "Student not found")
            }
    )
    ResponseEntity<Student> getStudent(@PathVariable int ru) {
        Student student = this.studentService.getStudent(ru);
        if(student == null)
            return ResponseEntity.badRequest().body(null);

        return ResponseEntity.ok(student);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new student",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Student created successfully")
            }
    )
    Student postStudent(@RequestBody Student student){
        this.studentService.students.add(student);
        return student;
    }

    @DeleteMapping("/{ru}")
    @Operation(
            summary = "Delete a student",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
                    @ApiResponse(responseCode = "400", description = "Student not found")
            }
    )
    ResponseEntity<?> deleteStudent(@PathVariable int ru){
        boolean result = this.studentService.students.removeIf(s -> s.getRu() == ru);
        if(!result)
            return ResponseEntity.badRequest().body(null);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{ru}")
    @Operation(
            summary = "Update student completely",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student updated"),
                    @ApiResponse(responseCode = "400", description = "Student not found")
            }
    )
    Student putStudent(@PathVariable int ru, @RequestBody Student student){
        return null; // Puedes completarlo luego
    }

    @PatchMapping("/{ru}")
    @Operation(
            summary = "Partially update a student",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student patched successfully"),
                    @ApiResponse(responseCode = "400", description = "Student not found")
            }
    )
    ResponseEntity<Student> patchStudent(@PathVariable int ru, @RequestBody Map<String, Object> update){
        Student student = this.studentService.getStudent(ru);
        if(student == null)
            return ResponseEntity.badRequest().body(null);

        update.forEach((key, value) -> {
            switch (key) {
                case "name":
                    student.setName((String) value);
                    break;
            }
        });
        return ResponseEntity.ok(student);
    }
}
