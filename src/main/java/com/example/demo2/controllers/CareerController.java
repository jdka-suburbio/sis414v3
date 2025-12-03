package com.example.demo2.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.demo2.models.Career;
import com.example.demo2.models.dto.CareerRequestDto;
import com.example.demo2.repository.CareerRepository;
import com.example.demo2.services.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/career")
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://jdka-suburbio.github.io"})
@Tag(name="Career", description="This endpoint permits create, read, update and delete operations")
public class CareerController {
    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private CareerService careerService;

    @GetMapping
    List<Career> getCareers(){
        return careerRepository.findAll();
    }

    @PostMapping
    @Operation(
            summary = "Create a new career",
            tags = {"Career"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Career created successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    ResponseEntity<String> postCareer(@RequestBody CareerRequestDto career){
        try {
            if (career == null || career.getName() == null || career.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Error: Datos inválidos");
            }
            var reponseCareer = careerService.createCareerWithRelations(career);
            if(reponseCareer == null)
                return ResponseEntity.badRequest().body("Error: No se pudo crear la carrera");
            return ResponseEntity.status(201).body("Carrera agregada con id " + reponseCareer.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    String deleteCareer(@PathVariable Long id)
    {
        if(!careerRepository.existsById(id))
        {
            return "Career not found";
        }
        careerRepository.deleteById(id);
        return  "Career deleted successfully";
    }
}
