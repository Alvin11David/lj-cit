package org.example.capstoneapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CourseRequest(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Code cannot be blank")
        String code,

        @Pattern(regexp = "^[A-Z0-Z9#_€]", message = "Password may contain only characters, numbers, underscore and other symbols")
        String password,

        @NotBlank(message = "Email is required")
        @Email(message = "The Email must be valid!")
        String email
) {
}
