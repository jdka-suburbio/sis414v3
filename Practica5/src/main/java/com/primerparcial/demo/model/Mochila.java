package com.primerparcial.demo.model;

public class Mochila {

    private int id;
    private String marca;
    private int capacidadLitros;
    private boolean tieneRuedas;

    // Constructor vacío (obligatorio para Spring)
    public Mochila() {
    }

    // Constructor con parámetros
    public Mochila(int id, String marca, int capacidadLitros, boolean tieneRuedas) {
        this.id = id;
        this.marca = marca;
        this.capacidadLitros = capacidadLitros;
        this.tieneRuedas = tieneRuedas;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public int getCapacidadLitros() { return capacidadLitros; }
    public void setCapacidadLitros(int capacidadLitros) { this.capacidadLitros = capacidadLitros; }

    public boolean isTieneRuedas() { return tieneRuedas; }
    public void setTieneRuedas(boolean tieneRuedas) { this.tieneRuedas = tieneRuedas; }

    @Override
    public String toString() {
        return "Mochila{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", capacidadLitros=" + capacidadLitros +
                ", tieneRuedas=" + tieneRuedas +
                '}';
    }
}
