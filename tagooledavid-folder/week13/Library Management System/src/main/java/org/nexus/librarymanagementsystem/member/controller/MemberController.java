package org.nexus.librarymanagementsystem.member.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.nexus.librarymanagementsystem.book.dto.ApiResponse;
import org.nexus.librarymanagementsystem.member.dto.MemberRequest;
import org.nexus.librarymanagementsystem.member.dto.MemberResponse;
import org.nexus.librarymanagementsystem.member.facade.MemberFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {
  private final MemberFacade memberFacade;

  public MemberController(MemberFacade memberFacade) {
    this.memberFacade = memberFacade;
  }

  @PostMapping
  public ResponseEntity<ApiResponse<MemberResponse>> create(
      @Valid @RequestBody MemberRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            new ApiResponse<>(
                "SUCCESS", "Member created successfully", memberFacade.create(request)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<MemberResponse>> findById(@PathVariable Long id) {
    return ResponseEntity.ok(
        new ApiResponse<>("SUCCESS", "Member fetched successfully", memberFacade.findById(id)));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<MemberResponse>>> findAll() {
    return ResponseEntity.ok(
        new ApiResponse<>("SUCCESS", "Members fetched successfully", memberFacade.findAll()));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<MemberResponse>> update(
      @PathVariable Long id, @Valid @RequestBody MemberRequest request) {
    return ResponseEntity.ok(
        new ApiResponse<>(
            "SUCCESS", "Member updated successfully", memberFacade.update(id, request)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    memberFacade.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
