package com.example.demo2.controllers;

import com.example.demo2.models.Student;
import com.example.demo2.repository.StudentRepository;
import com.example.demo2.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    ResponseEntity<List<Student>> getStudents(){
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("/{nameCareer}")
    public ResponseEntity<List<Student>> getStudentByCareerName(
            @RequestParam(required = false, defaultValue = "*") String nameCareer) {
        List<Student> students;
        if (nameCareer.equals("*")) {
            students = studentService.getStudents();
        } else {
            students = studentService.getStudentsByCareerName(nameCareer);
        }
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{ru}")
    ResponseEntity<Student> getStudentByRu(@PathVariable Long ru) {
        Student student = studentService.getStudentByRu(ru);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    ResponseEntity<?> postStudent(@RequestBody Student student){
        Student saved = studentService.createStudent(student);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{ru}")
                .buildAndExpand(student.getRu())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("/{ru}")
    ResponseEntity<?> deleteStudent(@PathVariable Long ru){
        boolean result = studentService.deleteStudent(ru);
        if(!result){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{ru}")
    ResponseEntity<?> putStudent(@PathVariable Long ru, @RequestBody Student student){
        boolean updateStudent = studentService.updateStudent(ru, student);
        if(!updateStudent){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{ru}")
    public ResponseEntity<?> patchStudent(@PathVariable Long ru, @RequestBody Student student) {
        return studentService.partiallyUpdateStudent(ru, student)
                .map(updated -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
