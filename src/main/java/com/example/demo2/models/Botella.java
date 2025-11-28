package com.example.demo2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "botella")
public class Botella {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String tipo;
    private Double capacidadLitros;
    private Boolean vacia;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesa_id")
    @JsonBackReference
    private Mesa mesa;

    public Botella() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getCapacidadLitros() {
        return capacidadLitros;
    }

    public void setCapacidadLitros(Double capacidadLitros) {
        this.capacidadLitros = capacidadLitros;
    }

    public Boolean getVacia() {
        return vacia;
    }

    public void setVacia(Boolean vacia) {
        this.vacia = vacia;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}