package com.audifonos.quintapractica.models;

public class Audifono {
    private Long id;
    private String marca;
    private String modelo;
    private Double precio;
    private Boolean inalambrico;


    public Audifono() {
    }


    public Audifono(Long id, String marca, String modelo, Double precio, Boolean inalambrico) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.inalambrico = inalambrico;
    }


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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Boolean getInalambrico() {
        return inalambrico;
    }

    public void setInalambrico(Boolean inalambrico) {
        this.inalambrico = inalambrico;
    }
}
