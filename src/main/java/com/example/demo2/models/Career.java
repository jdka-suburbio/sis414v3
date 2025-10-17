package com.example.demo2.models;

import java.util.ArrayList;
import java.util.List;

public class Career {
    String Name;
    String Description;
    String Institution;
    //List<Subject> Subjects;

    public Career(String description, String institution, String name) {
        Description = description;
        Institution = institution;
        Name = name;
        //Subjects = new ArrayList<Subject>();
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
}
