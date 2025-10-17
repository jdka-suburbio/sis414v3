package com.example.Practica5.controllers;

import com.example.Practica5.models.Pillow;
import com.example.Practica5.services.PillowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pillows")
public class PillowController {

    private final PillowService pillowService;

    public PillowController(PillowService pillowService){
        this.pillowService = pillowService;
    }

    @GetMapping
    public ResponseEntity<List<Pillow>> getPillows(){
        return ResponseEntity.ok(pillowService.getPillows());
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getPillowByCode(@PathVariable int code) {
        Optional<Pillow> pillow = pillowService.getPillowByCode(code);
        if (pillow.isPresent()) {
            return ResponseEntity.ok(pillow);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> postPillow(@RequestBody Pillow pillow){
        Pillow newPillow = pillowService.createPillow(pillow);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(pillow.getPillowCode())
                .toUri();
        return ResponseEntity.created(location).body(newPillow);
    }

    @PutMapping("/{code}")
    public ResponseEntity<?> putPillow(@PathVariable int code, @RequestBody Pillow pillow) {
        boolean updatePillow = pillowService.updatePillow(code, pillow);
        if (updatePillow)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{code}")
    public ResponseEntity<?>patchPillow(@PathVariable int code, @RequestBody Pillow pillow) {
        boolean partiallyUpdate = pillowService.partiallyUpdatePillow(code, pillow);
        if(partiallyUpdate)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<?> deletePillow(@PathVariable int code){
        boolean removed = pillowService.deletePillow(code);
        if(removed){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
