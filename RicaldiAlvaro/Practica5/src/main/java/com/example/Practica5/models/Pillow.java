package com.example.Practica5.models;

public class Pillow {
    private int pillowCode;
    private String image;
    private String color;
    private double price;
    private double length;
    private double width;

    public Pillow(int pillowCode, String image, String color, double price, double length, double width) {
        this.pillowCode = pillowCode;
        this.image = image;
        this.color = color;
        this.price = price;
        this.length = length;
        this.width = width;
    }

    public int getPillowCode() {
        return pillowCode;
    }

    public void setPillowCode(int pillowCode) {
        this.pillowCode = pillowCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String material) {
        this.image = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
