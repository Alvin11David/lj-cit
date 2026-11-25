package org.nexus.librarymanagementsystem.common.security.controller;

import org.nexus.librarymanagementsystem.book.dto.ApiResponse;
import org.nexus.librarymanagementsystem.common.security.CustomUserDetails;
import org.nexus.librarymanagementsystem.common.security.CustomUserDetailsService;
import org.nexus.librarymanagementsystem.common.security.JwtService;
import org.nexus.librarymanagementsystem.common.security.dto.LoginRequest;
import org.nexus.librarymanagementsystem.common.security.dto.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final CustomUserDetailsService customUserDetailsService;

  public AuthController(
      AuthenticationManager authenticationManager,
      JwtService jwtService,
      CustomUserDetailsService customUserDetailsService) {
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
    this.customUserDetailsService = customUserDetailsService;
  }

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody LoginRequest loginRequest) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
    CustomUserDetails customUserDetails =
        (CustomUserDetails) customUserDetailsService.loadUserByUsername(loginRequest.email());
    String token = jwtService.generateToken(customUserDetails);
    ApiResponse<TokenResponse> body =
        new ApiResponse<>("SUCCESSFUL", "LOGIN OK", new TokenResponse(token));

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(body);
  }

  /*
  method of register
  but then it will be hitting the add student endpoint

  * */
}
