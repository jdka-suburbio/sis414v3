package com.example.demo2.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.demo2.models.Mesa;
import com.example.demo2.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RequestMapping("/mesa")
@RestController
@CrossOrigin(origins = "https://paolaquispe-2025.github.io")
@Tag(name="Mesa", description="Endpoints para operaciones CRUD de mesas")
public class MesaController {

    @Autowired
    private MesaRepository mesaRepository;

    @GetMapping
    public List<Mesa> getMesas(){
        return mesaRepository.findAll();
    }

    @PostMapping
    @Operation(
            summary = "Crear una nueva mesa",
            tags = {"Mesa"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Mesa creada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
            }
    )
    public ResponseEntity<Mesa> postMesa(@RequestBody Mesa mesa){
        var responseMesa = mesaRepository.save(mesa);
        if(responseMesa == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.created(null).body(responseMesa);
    }

    @DeleteMapping("/{id}")
    public String deleteMesa(@PathVariable Long id) {
        if(!mesaRepository.existsById(id)) {
            return "Mesa no encontrada";
        }
        mesaRepository.deleteById(id);
        return "Mesa eliminada exitosamente";
    }
}