package org.example.capstoneapi.facade;


import org.example.capstoneapi.dto.CourseRequest;
import org.example.capstoneapi.dto.CourseResponse;
import org.example.capstoneapi.mapper.CourseMapper;
import org.example.capstoneapi.model.Course;
import org.example.capstoneapi.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseFacade {
    private final CourseService courseService;
    private final CourseMapper courseMapper;

    public CourseFacade(CourseService courseService, CourseMapper courseMapper){
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    @Transactional
    public CourseResponse save(CourseRequest request){
        Course course = courseMapper.toEntity(request);
        Course savedCourse = courseService.save(course);
        return courseMapper.toResponse(savedCourse);
    }


    @Transactional(readOnly = true)
    public List<CourseResponse> findAll(){
        return courseService.findAll().stream().map(courseMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CourseResponse findById(Long id){
        Course course = courseService.findById(id);
        return courseMapper.toResponse(course);
    }


    @Transactional
    public CourseResponse updateCourse(Long id, CourseRequest request){
        Course course = courseService.findById(id);
        course.setCode(request.code());
        course.setName(request.name());

        Course updatedCourse = courseService.save(course);

        return courseMapper.toResponse(updatedCourse);
    }

    public void deleteById(Long id){
        courseService.deleteById(id);
    }


}
