package org.nexus.librarymanagementsystem.common.exception;

import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.nexus.librarymanagementsystem.book.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(MemberNotFoundException.class)
  public ResponseEntity<ApiResponse<Object>> handleNotFound(MemberNotFoundException exception) {
    ApiResponse<Object> body = new ApiResponse<>("MEMBER NOT FOUND", exception.getMessage(), null);

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Object>> handleValidation(
      MethodArgumentNotValidException exception) {
    List<Map<String, String>> errors =
        exception.getBindingResult().getFieldErrors().stream()
            .map(f -> Map.of("field", f.getField(), "message", f.getDefaultMessage()))
            .toList();

    ApiResponse<Object> body =
        new ApiResponse<>("BAD_REQUEST", "Validation Error", Map.of("errors", errors));

    return ResponseEntity.badRequest().body(body);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Object>> handleUnExpected(Exception exception) {
    log.error("Unexpected Error", exception);
    ApiResponse<Object> body =
        new ApiResponse<>("INTERNAL SERVER ERROR", "Something Went Wrong", null);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }

  @ExceptionHandler(LoanNotFoundException.class)
  public ResponseEntity<ApiResponse<Object>> handleNotFound(LoanNotFoundException exception) {
    ApiResponse<Object> body = new ApiResponse<>("LOAN NOT FOUND", exception.getMessage(), null);

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

  @ExceptionHandler(BookNotFoundException.class)
  public ResponseEntity<ApiResponse<Object>> handleNotFound(BookNotFoundException exception) {
    ApiResponse<Object> body = new ApiResponse<>("BOOK NOT FOUND", exception.getMessage(), null);

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }
}
