package org.nexus.librarymanagementsystem.loan.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.nexus.librarymanagementsystem.book.dto.ApiResponse;
import org.nexus.librarymanagementsystem.loan.dto.LoanRequest;
import org.nexus.librarymanagementsystem.loan.dto.LoanResponse;
import org.nexus.librarymanagementsystem.loan.facade.LoanFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {
  private final LoanFacade loanFacade;

  public LoanController(LoanFacade loanFacade) {
    this.loanFacade = loanFacade;
  }

  @PostMapping
  public ResponseEntity<ApiResponse<LoanResponse>> create(@Valid @RequestBody LoanRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            new ApiResponse<>("SUCCESS", "Loan created successfully", loanFacade.create(request)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<LoanResponse>> findById(@PathVariable Long id) {
    return ResponseEntity.ok(
        new ApiResponse<>("SUCCESS", "Loan fetched successfully", loanFacade.findById(id)));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<LoanResponse>>> findAll() {
    return ResponseEntity.ok(
        new ApiResponse<>("SUCCESS", "Loans fetched successfully", loanFacade.findAll()));
  }

  @PutMapping("/{id}/return")
  public ResponseEntity<ApiResponse<LoanResponse>> returnLoan(@PathVariable Long id) {
    return ResponseEntity.ok(
        new ApiResponse<>("SUCCESS", "Loan returned successfully", loanFacade.returnLoan(id)));
  }
}
