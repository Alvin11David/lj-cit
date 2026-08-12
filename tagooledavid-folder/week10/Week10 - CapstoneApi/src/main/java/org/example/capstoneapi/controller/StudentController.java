package org.example.capstoneapi.controller;


import jakarta.validation.Valid;
import org.example.capstoneapi.dto.ApiResponse;
import org.example.capstoneapi.dto.StudentRequest;
import org.example.capstoneapi.dto.StudentResponse;
import org.example.capstoneapi.facade.StudentFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentFacade studentFacade;

    public StudentController(StudentFacade studentFacade){
        this.studentFacade = studentFacade;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> create(@Valid @RequestBody StudentRequest request){
        StudentResponse created = studentFacade.create(request);
        ApiResponse<StudentResponse> body = new ApiResponse<>(
                "SUCCESS",
                "Student Created Successfully",
                created
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getById(@PathVariable Long id){
        StudentResponse student = studentFacade.getById(id);
        ApiResponse<StudentResponse> body = new ApiResponse<>(
                "SUCCESS",
                "Student found",
                student
        );
        return ResponseEntity.ok(body);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAll(){
        List<StudentResponse> allStudents = studentFacade.getAll();
        ApiResponse<List<StudentResponse>> body = new ApiResponse<>(
                "SUCCESS",
                "Students fetched Successfully",
                allStudents
        );
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> update(@PathVariable Long id, @Valid @RequestBody StudentRequest request){
        StudentResponse updatedStudent = studentFacade.update(id,request);
        ApiResponse<StudentResponse> body = new ApiResponse<>(
                "SUCCESS",
                "Student Updated Successfully",
                updatedStudent
        );
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id){
        studentFacade.delete(id);
        ApiResponse<Void> body = new ApiResponse<>(
                "SUCCESS",
                "Student Deleted Successfully",
                null
        );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(body);
    }

}
