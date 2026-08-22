package org.example.capstoneapi.controller;


import jakarta.validation.Valid;
import org.example.capstoneapi.dto.ApiResponse;
import org.example.capstoneapi.dto.CourseRequest;
import org.example.capstoneapi.dto.CourseResponse;
import org.example.capstoneapi.dto.StudentResponse;
import org.example.capstoneapi.facade.CourseFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseFacade courseFacade;

    public CourseController(CourseFacade courseFacade){
        this.courseFacade = courseFacade;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponse>> create(@Valid @RequestBody CourseRequest request){
        CourseResponse createdCourse = courseFacade.save(request);
        ApiResponse<CourseResponse> body = new ApiResponse<>(
                "SUCCESS",
                "Course created successfully",
                createdCourse
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
