package org.example.capstoneapi.repository;


import org.example.capstoneapi.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {

    @Query("SELECT e FROM Enrollment e JOIN FETCH e.student JOIN FETCH e.course")
    List<Enrollment> findAllWithStudentAndCourse();
}
