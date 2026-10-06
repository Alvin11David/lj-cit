package org.example.capstoneapi.service;

import org.example.capstoneapi.model.Course;
import org.example.capstoneapi.repository.CourseRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CoursesService {
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    public CoursesService(CourseRepository courseRepository, PasswordEncoder passwordEncoder) {
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Course createNewCourse(String name, String hashPassword) {
        Course course = new Course();

        course.setName(name);
        course.setHashPassword(passwordEncoder.encode(hashPassword));
        return courseRepository.save(course);
    }
}
