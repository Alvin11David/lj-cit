package org.example.capstoneapi.security.controller;


import org.example.capstoneapi.dto.ApiResponse;
import org.example.capstoneapi.security.CustomUserDetails;
import org.example.capstoneapi.security.CustomUserDetailsService;
import org.example.capstoneapi.security.JwtService;
import org.example.capstoneapi.security.dto.LoginRequest;
import org.example.capstoneapi.security.dto.TokenResponse;
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
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          CustomUserDetailsService customUserDetailsService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtService = jwtService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody LoginRequest loginRequest ){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(),loginRequest.password())
        );
        CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(loginRequest.email());
        String token = jwtService.generateToken(customUserDetails);

        ApiResponse<TokenResponse> body = new ApiResponse<>(
                "SUCCESS",
                "Obtained Token Successfully",
                new TokenResponse(token)
        );

        return ResponseEntity.ok(body);
    }
}
