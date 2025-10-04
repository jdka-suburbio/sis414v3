package com.example.demo2.models;

public class Student {
    int Ru;
    String Dni;
    String Name;
    String LastName;
    String Address;
    Career CareerObj;

    public Student(int ru, String dni, String name, String lastName, String address, Career careerObj) {
        Ru = ru;
        Dni = dni;
        Name = name;
        LastName = lastName;
        Address = address;
        CareerObj = careerObj;
    }

    public int getRu() {
        return Ru;
    }

    public void setRu(int ru) {
        Ru = ru;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Career getCareer() {
        return CareerObj;
    }

    public void setCareer(Career careerObj) {
        this.CareerObj = careerObj;
    }
}
