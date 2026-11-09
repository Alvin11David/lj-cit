package org.nexus.librarymanagementsystem.member.dto;

import java.time.LocalDateTime;

public record MemberResponse(Long id, String username, String email, LocalDateTime createdAt) {}
