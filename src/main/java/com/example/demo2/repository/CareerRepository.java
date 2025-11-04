package com.example.demo2.repository;

import com.example.demo2.models.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository  extends JpaRepository<Career, Long> {
}
