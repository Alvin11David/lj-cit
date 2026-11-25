package org.nexus.librarymanagementsystem.member.mapper;

import org.nexus.librarymanagementsystem.member.dto.MemberRequest;
import org.nexus.librarymanagementsystem.member.dto.MemberResponse;
import org.nexus.librarymanagementsystem.member.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
  public Member toEntity(MemberRequest request) {
    Member member = new Member();
    member.setUserName(request.username());
    member.setEmail(request.email());
    member.setPassword(request.password());
    member.setRole(request.role());
    return member;
  }

  public MemberResponse toResponse(Member member) {
    return new MemberResponse(
        member.getId(), member.getUserName(), member.getEmail(), member.getCreatedAt());
  }
}
