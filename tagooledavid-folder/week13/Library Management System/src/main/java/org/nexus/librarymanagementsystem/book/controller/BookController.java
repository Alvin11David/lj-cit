package org.nexus.librarymanagementsystem.book.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.nexus.librarymanagementsystem.book.dto.ApiResponse;
import org.nexus.librarymanagementsystem.book.dto.BookRequest;
import org.nexus.librarymanagementsystem.book.dto.BookResponse;
import org.nexus.librarymanagementsystem.book.dto.PageInfo;
import org.nexus.librarymanagementsystem.book.facade.BookFacade;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
  private final BookFacade bookFacade;

  public BookController(BookFacade bookFacade) {
    this.bookFacade = bookFacade;
  }

  @PostMapping
  public ResponseEntity<ApiResponse<BookResponse>> create(@Valid @RequestBody BookRequest request) {
    BookResponse created = bookFacade.create(request);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ApiResponse<>("SUCCESS", "Book created successfully", created));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<BookResponse>> findById(@PathVariable Long id) {
    return ResponseEntity.ok(
        new ApiResponse<>("SUCCESS", "Book fetched successfully", bookFacade.findById(id)));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<BookResponse>>> findPage(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "20") int size,
      @RequestParam(defaultValue = "title") String sortBy) {

    Page<BookResponse> result = bookFacade.findPage(page, size, sortBy);
    PageInfo pageInfo =
        new PageInfo(result.getTotalElements(), result.getNumber() + 1, result.getTotalPages());
    return ResponseEntity.ok(
        new ApiResponse<>("SUCCESS", "Books fetched successfully", result.getContent(), pageInfo));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<BookResponse>> update(
      @PathVariable Long id, @Valid @RequestBody BookRequest request) {
    return ResponseEntity.ok(
        new ApiResponse<>("SUCCESS", "Book updated successfully", bookFacade.update(id, request)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    bookFacade.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
