package org.example.capstoneapi.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRequest(

        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Registration number cannot be blank")
        String regNumber,

        @NotNull(message = "Gpa cannot be null")
        @DecimalMin(value = "0.0" ,message = "Gpa cannot be 0.0")
        @DecimalMax(value = "5.0", message = "Gpa cannot be greater than 5..")
        Double gpa
) {}
