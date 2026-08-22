package org.example.capstoneapi.controller;

import jakarta.validation.Valid;
import org.example.capstoneapi.dto.*;
import org.example.capstoneapi.facade.CourseFacade;
import org.example.capstoneapi.facade.EnrollmentFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {
    private final EnrollmentFacade enrollmentFacade;

    public EnrollmentController(EnrollmentFacade enrollmentFacade){
        this.enrollmentFacade = enrollmentFacade;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EnrollmentResponse>> create(@Valid @RequestBody EnrollmentRequest request){
        EnrollmentResponse created = enrollmentFacade.save(request);
        ApiResponse<EnrollmentResponse> body = new ApiResponse<>(
                "SUCCESS",
                "Enrollment created successfully",
                created
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<EnrollmentResponse>>> findAll(){
        List<EnrollmentResponse> allEnrollments = enrollmentFacade.findAll();

        ApiResponse<List<EnrollmentResponse>> body = new ApiResponse<>(
                "SUCCESS",
                "All Enrollments fetched successfully",
                allEnrollments
        );

        return ResponseEntity.ok(body);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EnrollmentResponse>> findById(@PathVariable Long id){
        EnrollmentResponse enrollment = enrollmentFacade.findById(id);
        ApiResponse<EnrollmentResponse> body = new ApiResponse<>(
                "SUCCESS",
                "Enrollment fetched successfully",
                enrollment
        );

        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EnrollmentResponse>> updateEnrollment(@PathVariable Long id, @Valid @RequestBody EnrollmentRequest enrollmentRequest){
        EnrollmentResponse enrollment = enrollmentFacade.update(id, enrollmentRequest);
        ApiResponse<EnrollmentResponse> body = new ApiResponse<>(
                "SUCCESS",
                "Enrollment updated successfully",
                enrollment
        );

        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEnrollment(@PathVariable Long id){
        enrollmentFacade.delete(id);
        ApiResponse<Void> body = new ApiResponse<>(
                "SUCCESS",
                "Enrollment deleted successfully",
                null
        );

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(body);
    }
}
