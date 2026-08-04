package org.example.capstoneapi.repository;

import org.example.capstoneapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface StudentRepository extends JpaRepository<Student,Long> {
}
