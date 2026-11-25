package org.nexus.librarymanagementsystem.common.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.nexus.librarymanagementsystem.common.security.model.Role;

public record RegisterRequest(
        @NotBlank(message = "Username cannot be blank") String username,
        @NotBlank(message = "Email cannot be blank") @Email(message = "Must be a valid email address")
        String email,
        @NotBlank(message = "Password cannot be blank") String password,
        @NotBlank(message = "Role cannot be blank") Role role
) {
}
