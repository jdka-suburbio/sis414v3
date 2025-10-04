package com.example.demo2.services;

import com.example.demo2.models.Career;
import com.example.demo2.models.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    public Career carrer = new Career("","UATF","Ing. Sistemas");
    public List<Student> students = new ArrayList<Student>();

    public StudentService() {
        Student student1 = new Student(1234,"1234","Jhon","Rambo","EEUU", carrer);
        Student student2 = new Student(4321,"4321","Pepe","Perez","NONE", carrer);
        Student student3 = new Student(6666,"6666","Juanito","wolf","NONE", carrer);
        students.add(student1);
        students.add(student2);
        students.add(student3);
    }

    public Student getStudent(int ru)
    {
        return this.students
                .stream()
                .filter(s -> s.getRu() == ru)
                .findFirst().orElse(null);
    }
}
