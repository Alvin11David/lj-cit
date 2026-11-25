package org.nexus.librarymanagementsystem.member.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.nexus.librarymanagementsystem.common.security.model.Role;

public record MemberRequest(
    @NotBlank(message = "Username cannot be blank") String username,
    @NotBlank(message = "Email cannot be blank") @Email(message = "Must be a valid email address")
        String email,
    @NotBlank(message = "Password cannot be blank") String password,
    @NotNull(message = "Role cannot be blank") Role role) {}
