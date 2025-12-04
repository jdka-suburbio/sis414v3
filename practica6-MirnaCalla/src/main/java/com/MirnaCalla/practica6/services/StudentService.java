package com.MirnaCalla.practica6.services;

import com.MirnaCalla.practica6.models.Career;
import com.MirnaCalla.practica6.models.Student;
import com.MirnaCalla.practica6.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student){
        studentRepository.save(student);
        return student;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByCareerName(String nameCareer) {
        return studentRepository.findAll().stream()
                .filter(s -> s.getCareer().getName().equalsIgnoreCase(nameCareer))
                .toList();
    }

    public Student getStudentByRu(Long ru) {
        return studentRepository.findById(ru).orElse(null);
    }

    public boolean updateStudent(Long ru, Student student){
        if(studentRepository.existsById(ru)){
            student.setRu(ru);
            studentRepository.save(student);
            return true;
        }
        return false;
    }

    public Optional<Student> partiallyUpdateStudent(Long ru, Student student) {
        return studentRepository.findById(ru).map(item -> {
            if (student.getDni() != null) item.setDni(student.getDni());
            if (student.getName() != null) item.setName(student.getName());
            if (student.getLastName() != null) item.setLastName(student.getLastName());
            if (student.getAddress() != null) item.setAddress(student.getAddress());
            if (student.getCareer() != null) item.setCareer(student.getCareer());
            return studentRepository.save(item);
        });
    }


    public boolean deleteStudent(Long ru) {
        if (!studentRepository.existsById(ru)) {
            return false;
        }
        studentRepository.deleteById(ru);
        return true;
    }


}
