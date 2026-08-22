package org.example.capstoneapi.exception;


import org.example.capstoneapi.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> Map.of("field", e.getField(), "message", e.getDefaultMessage()))
                .toList();

        ApiResponse<Object> body = new ApiResponse<>(
                "BAD_REQUEST",
                "Validation error",
                Map.of("errors", errors)
        );
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(StudentNotfoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleStudentNotFound(StudentNotfoundException ex) {
        ApiResponse<Object> body = new ApiResponse<>("NOT_FOUND", ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(CourseNotfoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleCourseNotFound(CourseNotfoundException ex) {
        ApiResponse<Object> body = new ApiResponse<>("NOT_FOUND", ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(EnrollmentNotfoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleEnrollmentNotFound(EnrollmentNotfoundException ex) {
        ApiResponse<Object> body = new ApiResponse<>("NOT_FOUND", ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> handleUnreadable(HttpMessageNotReadableException ex) {
        ApiResponse<Object> body = new ApiResponse<>("BAD_REQUEST", "Malformed or invalid request body", null);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleUnexpected(Exception ex) {
        ApiResponse<Object> body = new ApiResponse<>("INTERNAL_SERVER_ERROR", "Something went wrong", null);
        return ResponseEntity.status(500).body(body);
    }
}