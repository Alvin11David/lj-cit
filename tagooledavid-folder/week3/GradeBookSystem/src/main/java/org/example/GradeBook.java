package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class GradeBook {
    private Map<String, Student> studentDatabase = new HashMap<>();
    private static int studentCounter = 0;


    // incomplete method
    public void registerStudent(String email, String name) {
        studentCounter++;
        String generatedId = "R1-" + studentCounter;
        Student newStudent = new Student(generatedId, email, name);
        studentDatabase.put(generatedId, newStudent);
        System.out.println("Student " + name + " registered with ID: " + generatedId);
    }

    public void showAllStudents() {
        if (studentDatabase.isEmpty()) {
            System.out.println("Database is empty...");
            return;
        } else {
            System.out.println("********************** Students in the Database **********************");
            for (Student student : studentDatabase.values()) {
                System.out.println("Name: " + student.getName() + "   Email: " + student.getEmail());
            }
        }
    }

    public Map<String, String> getStudentNamesById() {
        Map<String, String> idToStudent = new HashMap<>();

        for (Student s : studentDatabase.values()) {
            idToStudent.put(s.getId(), s.getName());
        }

        return idToStudent;
    }

    public Student getStudent(String id) {
        return studentDatabase.get(id);
    }

    public void clearDatabase() {
        studentDatabase.clear();
    }

    public void deleteStudent(String id) {
        studentDatabase.remove(id);
    }


}
