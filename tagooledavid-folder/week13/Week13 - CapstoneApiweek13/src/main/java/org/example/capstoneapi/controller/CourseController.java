package org.example.capstoneapi.controller;


import jakarta.validation.Valid;
import org.example.capstoneapi.dto.ApiResponse;
import org.example.capstoneapi.dto.CourseRequest;
import org.example.capstoneapi.dto.CourseResponse;
import org.example.capstoneapi.dto.StudentResponse;
import org.example.capstoneapi.facade.CourseFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponse>>> findAll(){
        List<CourseResponse> allCourses = courseFacade.findAll();

        ApiResponse<List<CourseResponse>> body = new ApiResponse<>(
                "SUCCESS",
                "All Courses fetched successfully",
                allCourses
        );

        return ResponseEntity.ok(body);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> findById(@PathVariable Long id){
        CourseResponse course = courseFacade.findById(id);
        ApiResponse<CourseResponse> foundCourse = new ApiResponse<>(
                "SUCCESS",
                "Course fetched successfully",
                course
        );

        return ResponseEntity.ok(foundCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequest courseRequest){
        CourseResponse course = courseFacade.updateCourse(id,courseRequest);
        ApiResponse<CourseResponse> body = new ApiResponse<>(
                "SUCCESS",
                "Course updated successfully",
                course
        );

        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable Long id){
        courseFacade.deleteById(id);
        ApiResponse<Void> body = new ApiResponse<>(
                "SUCCESS",
                "Course deleted successfully",
                null
        );

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(body);
    }
}
