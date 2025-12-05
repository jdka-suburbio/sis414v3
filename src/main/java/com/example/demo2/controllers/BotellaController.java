package com.example.demo2.controllers;

import com.example.demo2.models.Botella;
import com.example.demo2.repository.BotellaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appi/botella")
@CrossOrigin(origins = "https://paolaquispe-2025.github.io")
public class BotellaController {

    @Autowired
    private BotellaRepository botellaRepository;

    // --------------------------
    // 1. MOSTRAR TODAS LAS BOTELLAS
    // --------------------------
    @GetMapping
    public List<Botella> getBotellas() {
        return botellaRepository.findAll();
    }

    // --------------------------
    // 2. MOSTRAR UNA BOTELLA POR ID
    // --------------------------
    @GetMapping("/{id}")
    public Optional<Botella> getBotellaById(@PathVariable Long id) {
        return botellaRepository.findById(id);
    }

    // --------------------------
    // 3. CREAR BOTELLA
    // --------------------------
    @PostMapping
    public Botella postBotella(@RequestBody Botella botella) {
        return botellaRepository.save(botella);
    }

    // --------------------------
    // 4. EDITAR BOTELLA
    // --------------------------
    @PutMapping("/{id}")
    public Botella updateBotella(@PathVariable Long id, @RequestBody Botella botella) {
        return botellaRepository.findById(id)
                .map(b -> {
                    b.setMarca(botella.getMarca());
                    b.setTipo(botella.getTipo());
                    b.setCapacidadLitros(botella.getCapacidadLitros());
                    b.setVacia(botella.getVacia());
                    return botellaRepository.save(b);
                })
                .orElseGet(() -> {
                    botella.setId(id);
                    return botellaRepository.save(botella);
                });
    }

    // --------------------------
    // 5. ELIMINAR BOTELLA
    // --------------------------
    @DeleteMapping("/{id}")
    public void deleteBotella(@PathVariable Long id) {
        botellaRepository.deleteById(id);
    }
}
