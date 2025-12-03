package com.example.demo2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private Integer credits;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id")
    @JsonIgnore // Evita recursión Career -> Subjects -> Career ...
    private Career career;

    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore // Evita recursión Career -> Students -> Subjects -> Students ...
    private List<Student> students = new ArrayList<>();

    public Subject() {
    }

    public Subject(String name, String code, Integer credits, String description, Career career) {
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.description = description;
        this.career = career;
        this.students = new ArrayList<>();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
