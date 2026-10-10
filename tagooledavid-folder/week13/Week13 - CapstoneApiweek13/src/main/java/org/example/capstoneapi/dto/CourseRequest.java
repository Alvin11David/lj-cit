package org.example.capstoneapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CourseRequest(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Code cannot be blank")
        String code,

        @NotBlank(message = "Email is required")
        @Email(message = "The Email must be valid!")
        String email
) {
}
