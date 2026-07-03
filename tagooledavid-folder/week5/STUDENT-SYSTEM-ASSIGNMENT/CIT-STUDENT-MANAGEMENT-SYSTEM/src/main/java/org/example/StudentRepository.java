package org.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudentRepository {
    private final Map<String, Student> students = new HashMap<>();

    public void save(Student student) {
        students.put(student.getRegNo(), student);
    }

    public Student findByRegNo(String regNo) {
        return students.get(regNo);
    }

    public Collection<Student> findAll() {
        return students.values();
    }

    public void delete(String regNo) {
        students.remove(regNo);
    }

    public void clear() {
        students.clear();
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }
}