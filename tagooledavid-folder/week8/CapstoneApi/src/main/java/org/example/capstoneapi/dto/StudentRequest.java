package org.example.capstoneapi.dto;

import jakarta.validation.constraints.NotNull;

public record StudentRequest(
        @NotNull(message = "Name cannot be null")
        String name,
        String regNumber,
        double gpa
) {}
