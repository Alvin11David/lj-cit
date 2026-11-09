package org.nexus.librarymanagementsystem.book.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookRequest(
    @NotBlank(message = "Title cannot be blank") String title,
    @NotBlank(message = "Author cannot be blank") String author,
    @NotBlank(message = "ISBN cannot be blank") String isbn,
    @NotNull(message = "Total copies is required")
        @Min(value = 0, message = "Total copies cannot be negative")
        Integer totalCopies) {}
