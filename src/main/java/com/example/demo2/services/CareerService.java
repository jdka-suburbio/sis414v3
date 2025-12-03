package com.example.demo2.services;

import com.example.demo2.models.Career;
import com.example.demo2.models.Student;
import com.example.demo2.models.Subject;
import com.example.demo2.models.dto.CareerRequestDto;
import com.example.demo2.models.dto.StudentRequestDto;
import com.example.demo2.models.dto.SubjectRequestDto;
import com.example.demo2.repository.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CareerService {

    @Autowired
    private CareerRepository careerRepository;

    public Career createCareerWithRelations(CareerRequestDto dto) {
        if (dto == null || dto.getName() == null || dto.getName().trim().isEmpty()) {
            return null;
        }

        // Crear la entidad Career principal
        Career career = new Career(
            dto.getDescription() != null ? dto.getDescription() : "",
            dto.getInstitution() != null ? dto.getInstitution() : "",
            dto.getName()
        );

        // ----- Materias -----
        List<Subject> subjectEntities = new ArrayList<>();
        Map<String, Subject> subjectByName = new HashMap<>();

        if (dto.getSubjects() != null) {
            for (SubjectRequestDto subjectDto : dto.getSubjects()) {
                Subject subject = new Subject();
                subject.setName(subjectDto.getName());
                subject.setCode(subjectDto.getCode());
                subject.setCredits(subjectDto.getCredits());
                subject.setDescription(subjectDto.getDescription());
                subject.setCareer(career);

                subjectEntities.add(subject);
                if (subject.getName() != null) {
                    subjectByName.put(subject.getName(), subject);
                }
            }
        }
        career.setSubjects(subjectEntities);

        // ----- Estudiantes -----
        List<Student> studentEntities = new ArrayList<>();

        if (dto.getStudents() != null) {
            for (StudentRequestDto studentDto : dto.getStudents()) {
                Student student = new Student();
                student.setFirstName(studentDto.getFirstName());
                student.setLastName(studentDto.getLastName());
                student.setEmail(studentDto.getEmail());
                student.setStudentCode(studentDto.getStudentCode());
                student.setCareer(career);

                // Asignar materias al estudiante según la lista de nombres
                if (studentDto.getSubjects() != null) {
                    for (String subjectName : studentDto.getSubjects()) {
                        Subject subject = subjectByName.get(subjectName);
                        if (subject != null) {
                            student.getSubjects().add(subject);
                            subject.getStudents().add(student);
                        }
                    }
                }

                studentEntities.add(student);
            }
        }
        career.setStudents(studentEntities);

        // Gracias a CascadeType.ALL en Career, se guardan también subjects y students
        return careerRepository.save(career);
    }
}


