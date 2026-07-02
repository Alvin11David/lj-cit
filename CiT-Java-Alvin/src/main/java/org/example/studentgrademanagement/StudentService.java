package org.example.studentgrademanagement;

import java.util.HashMap;
import java.util.Map;

public class StudentService {

    public Map<String, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        students.put(student.getRegistrationNumber(), student);
    }

    public Student findStudent(String registrationNumber) {
        return students.get(registrationNumber);
    }

    public void listStudents() {
        for(Student student : students.values()) {
            System.out.println(student);
        }
    }
}
