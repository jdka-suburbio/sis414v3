package com.MirnaCalla.practica6.repository;

import com.MirnaCalla.practica6.models.Student;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{
}
