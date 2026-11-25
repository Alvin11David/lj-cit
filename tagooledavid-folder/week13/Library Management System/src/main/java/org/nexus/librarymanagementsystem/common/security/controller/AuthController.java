package org.nexus.librarymanagementsystem.common.security.controller;

import jakarta.validation.Valid;
import org.nexus.librarymanagementsystem.book.dto.ApiResponse;
import org.nexus.librarymanagementsystem.common.security.dto.LoginRequest;
import org.nexus.librarymanagementsystem.common.security.dto.RegisterRequest;
import org.nexus.librarymanagementsystem.common.security.dto.TokenResponse;
import org.nexus.librarymanagementsystem.common.security.facade.AuthFacade;
import org.nexus.librarymanagementsystem.member.dto.MemberResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthFacade authFacade;

  public AuthController(AuthFacade authFacade) {
    this.authFacade = authFacade;
  }

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<TokenResponse>> login(
      @Valid @RequestBody LoginRequest loginRequest) {
    TokenResponse tokenResponse = authFacade.loginMember(loginRequest);
    ApiResponse<TokenResponse> body = new ApiResponse<>("SUCCESSFUL", "LOGIN OK", tokenResponse);

    return ResponseEntity.status(HttpStatus.OK).body(body);
  }

  @PostMapping("/register")
  public ResponseEntity<ApiResponse<MemberResponse>> register(
      @Valid @RequestBody RegisterRequest registerRequest) {
    MemberResponse memberResponse = authFacade.createMember(registerRequest);
    ApiResponse<MemberResponse> body =
        new ApiResponse<>("SUCCESSFUL", "REGISTER MEMBER OK", memberResponse);

    return ResponseEntity.status(HttpStatus.CREATED).body(body);
  }
}
