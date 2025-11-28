package com.example.demo2.repository;

import com.example.demo2.models.Botella;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotellaRepository extends JpaRepository<Botella, Long> {
}
