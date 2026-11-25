package org.nexus.librarymanagementsystem.common.security.facade;

import org.nexus.librarymanagementsystem.common.security.CustomUserDetails;
import org.nexus.librarymanagementsystem.common.security.CustomUserDetailsService;
import org.nexus.librarymanagementsystem.common.security.JwtService;
import org.nexus.librarymanagementsystem.common.security.dto.LoginRequest;
import org.nexus.librarymanagementsystem.common.security.dto.RegisterRequest;
import org.nexus.librarymanagementsystem.common.security.dto.TokenResponse;
import org.nexus.librarymanagementsystem.common.security.model.Role;
import org.nexus.librarymanagementsystem.member.dto.MemberRequest;
import org.nexus.librarymanagementsystem.member.dto.MemberResponse;
import org.nexus.librarymanagementsystem.member.facade.MemberFacade;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthFacade {
  private final MemberFacade memberFacade;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final CustomUserDetailsService customUserDetailsService;

  public AuthFacade(
      MemberFacade memberFacade,
      AuthenticationManager authenticationManager,
      JwtService jwtService,
      CustomUserDetailsService customUserDetailsService) {

    this.memberFacade = memberFacade;
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
    this.customUserDetailsService = customUserDetailsService;
  }


  public MemberResponse createMember(RegisterRequest registerRequest) {
    MemberRequest memberRequest =
        new MemberRequest(
            registerRequest.username(),
            registerRequest.email(),
            registerRequest.password(),
            Role.MEMBER);

    return memberFacade.create(memberRequest);
  }

  public TokenResponse loginMember(LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
    CustomUserDetails customUserDetails =
        (CustomUserDetails) authentication.getPrincipal(); //customUserDetailsService.loadUserByUsername(loginRequest.email());
    String token = jwtService.generateToken(customUserDetails);
    return new TokenResponse(token);
  }
}
