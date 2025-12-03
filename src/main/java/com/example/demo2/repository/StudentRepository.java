package com.example.demo2.repository;

import com.example.demo2.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentCode(String studentCode);
    List<Student> findByCareerIdOrderByLastNameAsc(Long careerId);
    Optional<Student> findByEmail(String email);
}
