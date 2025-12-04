package com.example.demo2.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.demo2.models.Career;
import com.example.demo2.repository.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/career")
@RestController
@Tag(name = "Careers", description = "CRUD operations for careers")
public class CareerController {

    @Autowired
    private CareerRepository careerRepository;

    @GetMapping
    @Operation(summary = "Get all careers", description = "Returns the full list of careers", responses = {
            @ApiResponse(responseCode = "200", description = "Careers obtained successfully")
    })
    List<Career> getCareers() {
        return careerRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Create a career", description = "Creates a new career and returns it", responses = {
            @ApiResponse(responseCode = "201", description = "Career created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided")
    })
    ResponseEntity<Career> postCareer(@RequestBody Career career) {
        Career savedCareer = careerRepository.save(career);
        if (savedCareer == null)
            return ResponseEntity.badRequest().body(null);

        return ResponseEntity.created(null).body(savedCareer);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a career", description = "Deletes a career by its ID", responses = {
            @ApiResponse(responseCode = "200", description = "Career deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Career not found")
    })
    String deleteCareer(@PathVariable Long id) {
        if (!careerRepository.existsById(id)) {
            return "Career not found";
        }
        careerRepository.deleteById(id);
        return "Career deleted successfully";
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing career", tags = { "Career" }, responses = {
            @ApiResponse(responseCode = "200", description = "Career updated successfully"),
            @ApiResponse(responseCode = "404", description = "Career not found")
    })
    ResponseEntity<Career> putCareer(@PathVariable Long id, @RequestBody Career career) {
        return careerRepository.findById(id)
                .map(existing -> {
                    existing.setName(career.getName());
                    existing.setDescription(career.getDescription());
                    Career updated = careerRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
