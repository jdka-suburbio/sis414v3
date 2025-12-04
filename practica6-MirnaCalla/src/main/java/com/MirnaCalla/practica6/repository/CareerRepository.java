package com.MirnaCalla.practica6.repository;

import com.MirnaCalla.practica6.models.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {
}
