package com.example.Week8Java;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository repository;

    public CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Course> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{code}")
    public Course getOne(@PathVariable String code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Course not found: " + code));
    }

    @PostMapping
    public Course create(@RequestBody Course course) {
        return repository.save(course);
    }

    @PutMapping("/{code}")
    public Course update(@PathVariable String code, @RequestBody Course updatedCourse) {
        Course course = repository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Course not found: " + code));
        course.setName(updatedCourse.getName());
        course.setCode(updatedCourse.getCode());
        return repository.save(course);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code) {
        Course course = repository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Course not found: " + code));
        repository.deleteById(course.getId());
    }
}