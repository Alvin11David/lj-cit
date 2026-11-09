package org.nexus.librarymanagementsystem.book.dto;

import java.time.LocalDateTime;

public record BookResponse(
    Long id, String title, String author, String isbn, int totalCopies, LocalDateTime createdAt) {}
