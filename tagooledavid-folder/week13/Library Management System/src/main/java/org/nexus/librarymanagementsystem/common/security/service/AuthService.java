package org.nexus.librarymanagementsystem.common.security.service;


import org.nexus.librarymanagementsystem.common.security.CustomUserDetailsService;
import org.nexus.librarymanagementsystem.common.security.JwtService;
import org.nexus.librarymanagementsystem.member.repository.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthService(MemberRepository memberRepository, AuthenticationManager authenticationManager,JwtService jwtService, CustomUserDetailsService customUserDetailsService){
        this.memberRepository = memberRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }


}
