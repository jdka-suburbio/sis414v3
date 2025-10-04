package com.example.demo2.controllers;

import com.example.demo2.models.Student;
import com.example.demo2.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/students")
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Student> getStudents(@RequestParam(required = false, defaultValue = "*") String nameCareer) {
        if(nameCareer.equals("*"))
            return this.studentService.students;
        return this.studentService.students.stream().filter(s->s.getCareer().getName().equals(nameCareer)).toList();
    }

    @GetMapping("/{ru}")
    ResponseEntity<Student> getStudent(@PathVariable int ru) {
        Student student = this.studentService.getStudent(ru);
        if(student == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Student postStudent(@RequestBody Student student){
        this.studentService.students.add(student);
        return student;
    }

    @DeleteMapping("/{ru}")
    ResponseEntity<?> deleteStudent(@PathVariable int ru){
        boolean result = this.studentService.students.removeIf(s-> s.getRu() == ru);
        if(!result)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{ru}")
    Student putStudent(@PathVariable int ru, @RequestBody Student student){

        return null;
    }

    @PatchMapping("/{ru}")
    ResponseEntity<Student> patchStudent(@PathVariable int ru, @RequestBody Map<String, Object> update){
        Student student = this.studentService.getStudent(ru);
        if(student == null)
            return ResponseEntity.badRequest().body(null);

        update.forEach((key,value) -> {
            switch (key)
            {
                case "name":
                    student.setName((String) value);
                break;
            }
        });
        return ResponseEntity.ok(student);
    }
}
