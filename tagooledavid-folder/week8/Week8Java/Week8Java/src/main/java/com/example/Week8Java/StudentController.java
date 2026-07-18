package com.example.Week8Java;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Student> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{regNo}")
    public Student getOne(@PathVariable String regNo) {
        return repository.findByRegNo(regNo)
                .orElseThrow(() -> new RuntimeException("Student not found: " + regNo));
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return repository.save(student);
    }

    @DeleteMapping("/{regNo}")
    public void delete(@PathVariable String regNo) {
        Student student = repository.findByRegNo(regNo)
                .orElseThrow(() -> new RuntimeException("Student not found: " + regNo));
        repository.deleteById(student.getId());
    }
}