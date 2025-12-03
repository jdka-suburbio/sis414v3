package com.example.demo2.services;

import com.example.demo2.models.Career;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CareerService {

    public List<Career> careers = new ArrayList<>();

    public CareerService() {

        careers.add(new Career("Estudio de software", "UATF", "Sistemas"));
        careers.add(new Career("Diseño de estructuras", "UMSA", "Arquitectura"));
        careers.add(new Career("Leyes y normas", "UPB", "Derecho"));
    }


    public Career getCareer(String name) {
        return this.careers.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}