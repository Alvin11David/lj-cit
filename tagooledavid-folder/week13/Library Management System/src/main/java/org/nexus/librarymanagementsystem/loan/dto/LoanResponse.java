package org.nexus.librarymanagementsystem.loan.dto;

import java.time.LocalDate;

public record LoanResponse(
    Long id,
    Long bookId,
    Long memberId,
    LocalDate dueDate,
    LocalDate returnedAt,
    LocalDate createdAt) {}
