package org.example.capstoneapi.dto;

public record ApiResponse<T>(
        String status,
        String message,
        T data
) {
}
