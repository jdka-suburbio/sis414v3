package com.example.demo2.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mesa")
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String ubicacion;
    private Integer capacidad;

    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Botella> botellas;

    public Mesa() {
        this.botellas = new ArrayList<>();
    }

    public Mesa(String numero, String ubicacion, Integer capacidad) {
        this.numero = numero;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.botellas = new ArrayList<>();
    }

    public Mesa(String numero, String ubicacion, Integer capacidad, List<Botella> botellas) {
        this.numero = numero;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.botellas = new ArrayList<>();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public List<Botella> getBotellas() {
        return botellas;
    }

    public void setBotellas(List<Botella> botellas) {
        this.botellas = botellas;
    }
}