package com.example.demo2.controllers;

import com.example.demo2.models.Career;
import com.example.demo2.services.CareerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/careers") // Ruta base
@Tag(name = "Carreras", description = "Controlador para gestionar las carreras universitarias")
public class CareerController {

    private final CareerService careerService;


    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener todas las carreras", description = "Retorna la lista completa de carreras disponibles")
    public List<Career> getCareers() {
        return this.careerService.careers;
    }


    @GetMapping("/{name}")
    @Operation(summary = "Obtener carrera por Nombre", description = "Busca una carrera específica usando su nombre")
    public ResponseEntity<Career> getCareer(@PathVariable String name) {
        Career career = this.careerService.getCareer(name);
        if (career == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(career);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear carrera", description = "Agrega una nueva carrera al sistema")
    public Career postCareer(@RequestBody Career career) {
        this.careerService.careers.add(career);
        return career;
    }


    @DeleteMapping("/{name}")
    @Operation(summary = "Eliminar carrera", description = "Elimina una carrera buscando por su nombre")
    public ResponseEntity<?> deleteCareer(@PathVariable String name) {

        boolean result = this.careerService.careers.removeIf(c -> c.getName().equalsIgnoreCase(name));

        if (!result) {
            return ResponseEntity.badRequest().body("No se encontró la carrera: " + name);
        }
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{name}")
    @Operation(summary = "Actualizar carrera", description = "Actualiza la descripción e institución de una carrera existente")
    public ResponseEntity<Career> putCareer(@PathVariable String name, @RequestBody Career careerUpdate) {
        Career existingCareer = this.careerService.getCareer(name);

        if (existingCareer == null) {
            return ResponseEntity.badRequest().body(null);
        }


        existingCareer.setDescription(careerUpdate.getDescription());
        existingCareer.setInstitution(careerUpdate.getInstitution());

        return ResponseEntity.ok(existingCareer);
    }
}