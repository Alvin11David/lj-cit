package org.example.capstoneapi.mapper;

import org.example.capstoneapi.dto.CourseRequest;
import org.example.capstoneapi.dto.CourseResponse;

import org.example.capstoneapi.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequest request){
        Course course = new Course();
        course.setName(request.name());
        course.setCode(request.code());
        return course;
    }


    public CourseResponse toResponse(Course course){
        return new CourseResponse(
                course.getId(),
                course.getName(),
                course.getCode()
        );
    }
}
