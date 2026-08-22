package org.example.capstoneapi.service;

import org.example.capstoneapi.exception.CourseNotfoundException;
import org.example.capstoneapi.model.Course;
import org.example.capstoneapi.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public Course save(Course course){
        return courseRepository.save(course);
    }


    public Course findById(Long id){
        return courseRepository.findById(id).orElseThrow(()->new CourseNotfoundException("Course not found with"+id));
    }

    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    public void deleteById(Long id){
        if (!courseRepository.existsById(id)){
            throw new CourseNotfoundException("Course not found with id:"+id);
        }
        courseRepository.deleteById(id);
    }
}
