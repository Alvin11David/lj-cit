package org.example;

import java.util.List;

public class StudentSubjectScoreRepository implements CRUDRepository<StudentSubjectScore,Integer> {

    @Override
    public void save(StudentSubjectScore entity) {

    }

    @Override
    public StudentSubjectScore findById(Integer integer) {
        return null;
    }

    @Override
    public List<StudentSubjectScore> loadAll() {
        return List.of();
    }
}
