package com.MirnaCalla.practica6.repository;

import com.MirnaCalla.practica6.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository <Subject, Long>{
}
