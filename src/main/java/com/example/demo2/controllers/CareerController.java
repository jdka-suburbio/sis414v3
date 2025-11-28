package com.example.demo2.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.demo2.models.Career;
import com.example.demo2.repository.CareerRepository;
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
    ResponseEntity<Career> postCareer(@RequestBody Career career){
        var reponseCareer =  careerRepository.save(career);
        if(reponseCareer == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.created(null).body(reponseCareer);
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
