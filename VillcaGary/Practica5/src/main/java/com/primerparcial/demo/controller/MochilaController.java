package com.primerparcial.demo.controller;

import com.primerparcial.demo.model.Mochila;
import com.primerparcial.demo.services.MochilaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/mochilas")
@RestController
public class MochilaController {

    private final MochilaService mochilaService;

    public MochilaController(MochilaService mochilaService) {
        this.mochilaService = mochilaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Mochila> getMochilas(@RequestParam(required = false, defaultValue = "*") String marca) {
        if (marca.equals("*"))
            return this.mochilaService.mochilas;
        return this.mochilaService.mochilas.stream()
                .filter(m -> m.getMarca().equalsIgnoreCase(marca))
                .toList();
    }

    @GetMapping("/{id}")
    ResponseEntity<Mochila> getMochila(@PathVariable int id) {
        Mochila mochila = this.mochilaService.getMochilaById(id);
        if (mochila == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(mochila);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mochila postMochila(@RequestBody Mochila mochila) {
        this.mochilaService.mochilas.add(mochila);
        return mochila;
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteMochila(@PathVariable int id) {
        boolean result = this.mochilaService.mochilas.removeIf(m -> m.getId() == id);
        if (!result)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    Mochila putMochila(@PathVariable int id, @RequestBody Mochila mochila) {
        Mochila existente = this.mochilaService.getMochilaById(id);
        if (existente != null) {
            existente.setMarca(mochila.getMarca());
            existente.setCapacidadLitros(mochila.getCapacidadLitros());
            existente.setTieneRuedas(mochila.isTieneRuedas());
        }
        return existente;
    }

    @PatchMapping("/{id}")
    ResponseEntity<Mochila> patchMochila(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        Mochila mochila = this.mochilaService.getMochilaById(id);
        if (mochila == null)
            return ResponseEntity.badRequest().body(null);

        updates.forEach((key, value) -> {
            switch (key) {
                case "marca":
                    mochila.setMarca((String) value);
                    break;
                case "capacidadLitros":
                    mochila.setCapacidadLitros((int) value);
                    break;
                case "tieneRuedas":
                    mochila.setTieneRuedas((boolean) value);
                    break;
            }
        });
        return ResponseEntity.ok(mochila);
    }
}
