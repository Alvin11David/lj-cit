package org.nexus.librarymanagementsystem.common.security.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "Email cannot be blank") String email,
    @NotBlank(message = "Email cannot be blank") String password) {}
