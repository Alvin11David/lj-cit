package org.example.capstoneapi.dto;

public record StudentResponse(
        Long id,
        String name,
        String regNumber,
        double gpa
) {
}
