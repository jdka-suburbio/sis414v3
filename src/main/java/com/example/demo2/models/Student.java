package com.example.demo2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Ru;
    String Dni;
    String Name;
    String LastName;
    String Address;

    @ManyToOne
    @JoinColumn(name = "career_id")
    Career CareerObj;

    public Student(Long ru, String dni, String name, String lastName, String address, Career careerObj) {
        Ru = ru;
        Dni = dni;
        Name = name;
        LastName = lastName;
        Address = address;
        CareerObj = careerObj;
    }

    public Student(){}

    public Long getRu() { return Ru; }
    public void setRu(Long ru) { Ru = ru; }

    public String getDni() { return Dni; }
    public void setDni(String dni) { Dni = dni; }

    public String getName() { return Name; }
    public void setName(String name) { Name = name; }

    public String getLastName() { return LastName; }
    public void setLastName(String lastName) { LastName = lastName; }

    public String getAddress() { return Address; }
    public void setAddress(String address) { Address = address; }

    public Career getCareer() { return CareerObj; }
    public void setCareer(Career careerObj) { this.CareerObj = careerObj; }
}
