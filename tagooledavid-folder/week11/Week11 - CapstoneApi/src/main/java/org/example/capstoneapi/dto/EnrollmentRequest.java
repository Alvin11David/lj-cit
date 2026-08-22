package org.example.capstoneapi.dto;

import jakarta.validation.constraints.NotNull;

public record EnrollmentRequest(

        @NotNull(message = "Student Id cannot be blank")
        Long studentId,

        @NotNull(message = "Course Id cannot be blank")
        Long courseId
) {
}
