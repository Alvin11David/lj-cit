package org.example.capstoneapi.dto;

import jakarta.validation.constraints.NotBlank;

public record CourseRequest(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Code cannot be blank")
        String code
) {
}
