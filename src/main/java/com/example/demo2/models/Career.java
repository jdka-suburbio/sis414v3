package com.example.demo2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String institution;

    public Career() { }

    public Career(String name, Long id, String description, String institution) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.institution = institution;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }
}
