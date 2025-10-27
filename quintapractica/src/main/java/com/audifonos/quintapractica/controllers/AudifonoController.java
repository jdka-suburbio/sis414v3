package com.audifonos.quintapractica.controllers;

import com.audifonos.quintapractica.models.Audifono;
import com.audifonos.quintapractica.services.AudifonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/audifonos")
public class AudifonoController {

    @Autowired
    private AudifonoService audifonoService;


    @GetMapping
    public List<Audifono> getAll() {
        return audifonoService.getAll();
    }


    @GetMapping("/{id}")
    public Audifono getById(@PathVariable Long id) {
        Audifono a = audifonoService.getById(id);
        if (a == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Audífono no encontrado");
        }
        return a;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Audifono create(@RequestBody Audifono body) {
        return audifonoService.create(body);
    }



    @PutMapping("/{id}")
    public Audifono replace(@PathVariable Long id, @RequestBody Audifono body) {
        // opcional: validar existencia previa
        Audifono exists = audifonoService.getById(id);
        if (exists == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Audífono no encontrado para PUT");
        }
        return audifonoService.replace(id, body);
    }



    @PatchMapping("/{id}")
    public Audifono patch(@PathVariable Long id, @RequestBody Map<String, Object> cambios) {
        Audifono actualizado = audifonoService.patch(id, cambios);
        if (actualizado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Audífono no encontrado para PATCH");
        }
        return actualizado;
    }

    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        boolean ok = audifonoService.delete(id);
        if (!ok) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Audífono no encontrado para DELETE");
        }
    }
}