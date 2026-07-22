package org.example.studentgrademanagement.controller;

import org.example.studentgrademanagement.model.ScoreRequest;
import org.example.studentgrademanagement.model.StudentResponse;
import org.example.studentgrademanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Map<String, Object> request) {
        String regNo = (String) request.get("registrationNumber");
        String name = (String) request.get("name");

        if (regNo == null || regNo.isBlank()) {
            return ResponseEntity.badRequest().body("registrationNumber is required");
        }
        if (name == null || name.isBlank()) {
            return ResponseEntity.badRequest().body("name is required");
        }

        Map<String, Double> scores = convertScores(request.get("scores"));
        if (scores == null || scores.isEmpty()) {
            return ResponseEntity.badRequest().body("scores are required");
        }

        try {
            StudentResponse response = studentService.createStudent(regNo, name, scores);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Double> convertScores(Object raw) {
        if (!(raw instanceof Map)) return null;
        Map<String, Double> result = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : ((Map<String, Object>) raw).entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Number) {
                result.put(entry.getKey(), ((Number) value).doubleValue());
            }
        }
        return result.isEmpty() ? null : result;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{registrationNumber}")
    public ResponseEntity<?> getStudentByRegistrationNumber(@PathVariable String registrationNumber) {
        try {
            StudentResponse response = studentService.findByRegistrationNumber(registrationNumber);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{registrationNumber}/scores")
    public ResponseEntity<?> updateScores(@PathVariable String registrationNumber,
                                           @RequestBody ScoreRequest request) {
        if (request.getScores() == null || request.getScores().isEmpty()) {
            return ResponseEntity.badRequest().body("scores are required");
        }

        try {
            StudentResponse response = studentService.recordOrUpdateScores(
                    registrationNumber, request.getScores());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("No student found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
