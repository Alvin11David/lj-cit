package org.nexus.librarymanagementsystem.member.facade;

import java.util.List;
import org.nexus.librarymanagementsystem.member.dto.MemberRequest;
import org.nexus.librarymanagementsystem.member.dto.MemberResponse;
import org.nexus.librarymanagementsystem.member.mapper.MemberMapper;
import org.nexus.librarymanagementsystem.member.model.Member;
import org.nexus.librarymanagementsystem.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberFacade {
  private final MemberService memberService;
  private final MemberMapper memberMapper;

  public MemberFacade(MemberService memberService, MemberMapper memberMapper) {
    this.memberService = memberService;
    this.memberMapper = memberMapper;
  }

  @Transactional
  public MemberResponse create(MemberRequest request) {
    return memberMapper.toResponse(memberService.save(memberMapper.toEntity(request)));
  }

  @Transactional(readOnly = true)
  public MemberResponse findById(Long id) {
    return memberMapper.toResponse(memberService.findById(id));
  }

  @Transactional(readOnly = true)
  public List<MemberResponse> findAll() {
    return memberService.findAll().stream().map(memberMapper::toResponse).toList();
  }

  @Transactional
  public MemberResponse update(Long id, MemberRequest request) {
    Member member = memberService.findById(id);
    member.setUserName(request.username());
    member.setEmail(request.email());
    return memberMapper.toResponse(memberService.save(member));
  }

  @Transactional
  public void deleteById(Long id) {
    memberService.deleteById(id);
  }
}
