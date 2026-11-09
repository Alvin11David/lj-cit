package org.nexus.librarymanagementsystem.loan.dto;

import jakarta.validation.constraints.NotNull;

public record LoanRequest(
    @NotNull(message = "Book id is required") Long bookId,
    @NotNull(message = "Member id is required") Long memberId) {}
