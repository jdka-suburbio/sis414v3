package com.example.demo2.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "career")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String Name;
    String Description;
    String Institution;
    //String UrlImage;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL)
    List<Subject> Subjects;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL)
    List<Student> Students;

    public Career(String description, String institution, String name) {
        Description = description;
        Institution = institution;
        Name = name;
        Subjects = new ArrayList<>();
        Students = new ArrayList<>();
    }

    public Career(){
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getInstitution() {
        return Institution;
    }

    public void setInstitution(String institution) {
        Institution = institution;
    }

    public List<Subject> getSubjects() {
        return Subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.Subjects = subjects;
    }

    public List<Student> getStudents() {
        return Students;
    }

    public void setStudents(List<Student> students) {
        this.Students = students;
    }
    /*
    public String getUrlImage() {
        return UrlImage;
    }

    public void setUrlImage(String urlImage) {
        UrlImage = urlImage;
    }
    */
}
