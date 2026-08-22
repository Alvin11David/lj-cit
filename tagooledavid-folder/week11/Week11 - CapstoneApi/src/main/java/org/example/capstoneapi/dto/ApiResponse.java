package org.example.capstoneapi.dto;

public record ApiResponse<T>(
        String status,
        String message,
        T data,
        Object meta
) {

    public ApiResponse(String status, String message, T data) {
        this(status, message, data, null);
    }
}
