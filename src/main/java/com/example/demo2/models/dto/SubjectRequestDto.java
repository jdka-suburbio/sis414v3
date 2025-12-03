package com.example.demo2.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectRequestDto {

    private String name;
    private String code;
    private Integer credits;
    private String description;

    // En el JSON viene como String, pero en la entidad es un objeto Career.
    private String career;

    // En el JSON, cada materia puede tener estudiantes anidados.
    // Para simplificar, no los usaremos para construir las relaciones,
    // pero los mapeamos para que el JSON se deserialice sin error.
    private List<StudentRequestDto> students;

    public SubjectRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public List<StudentRequestDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentRequestDto> students) {
        this.students = students;
    }
}


