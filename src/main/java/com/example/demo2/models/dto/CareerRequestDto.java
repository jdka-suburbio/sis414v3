package com.example.demo2.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CareerRequestDto {

    private String name;
    private String description;
    private String institution;

    private List<SubjectRequestDto> subjects;
    private List<StudentRequestDto> students;

    public CareerRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public List<SubjectRequestDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectRequestDto> subjects) {
        this.subjects = subjects;
    }

    public List<StudentRequestDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentRequestDto> students) {
        this.students = students;
    }
}


