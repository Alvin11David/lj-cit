package org.example.studentgrademanagement.repository;

import org.example.studentgrademanagement.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findByStudentStudentId(Long studentId);

    Optional<Score> findByStudentStudentIdAndSubjectSubjectId(Long studentId, Long subjectId);

    void deleteByStudentStudentId(Long studentId);
}
