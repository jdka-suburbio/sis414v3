package com.example.demo2.repository;

import com.example.demo2.models.Student;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{
}
