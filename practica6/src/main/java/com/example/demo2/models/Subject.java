package com.example.demo2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String Code;
    String Name;
    @Column(name = "theoryhours")
    int TheoryHours;

    public Subject() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getTheoryHours() {
        return TheoryHours;
    }

    public void setTheoryHours(int theoryHours) {
        TheoryHours = theoryHours;
    }
}
