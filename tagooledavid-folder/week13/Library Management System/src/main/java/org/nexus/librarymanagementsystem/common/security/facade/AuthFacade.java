package org.nexus.librarymanagementsystem.common.security.facade;

import org.nexus.librarymanagementsystem.common.security.CustomUserDetailsService;
import org.nexus.librarymanagementsystem.common.security.JwtService;
import org.nexus.librarymanagementsystem.member.dto.MemberResponse;
import org.nexus.librarymanagementsystem.member.mapper.MemberMapper;
import org.nexus.librarymanagementsystem.member.model.Member;
import org.nexus.librarymanagementsystem.member.repository.MemberRepository;
import org.nexus.librarymanagementsystem.member.service.MemberService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthFacade {
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthFacade(MemberService memberService, AuthenticationManager authenticationManager,MemberMapper memberMapper,JwtService jwtService, CustomUserDetailsService customUserDetailsService){
        this.memberService = memberService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
        this.memberMapper = memberMapper;
    }


    public MemberResponse createMember(){}


}
