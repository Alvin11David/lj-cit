package org.example.capstoneapi.repository;

import org.example.capstoneapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByGpaGreaterThanEqual(double gpa);

    Optional<Student> findByRegNumber(String regNumber);

    boolean existsByRegNumber(String regNumber);


    @Query("SELECT DISTINCT s FROM Student s JOIN FETCH s.enrollmentList")
    List<Student> findAllWithEnrollments();

}
