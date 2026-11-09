package org.nexus.librarymanagementsystem.member.service;

import java.util.List;
import org.nexus.librarymanagementsystem.common.exception.MemberNotFoundException;
import org.nexus.librarymanagementsystem.member.model.Member;
import org.nexus.librarymanagementsystem.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public Member save(Member member) {
    return memberRepository.save(member);
  }

  public Member findById(Long id) {
    return memberRepository
        .findById(id)
        .orElseThrow(() -> new MemberNotFoundException("No member found with id " + id));
  }

  public List<Member> findAll() {
    return memberRepository.findAll();
  }

  public void deleteById(Long id) {
    if (!memberRepository.existsById(id)) {
      throw new MemberNotFoundException("No member found with id " + id);
    }
    memberRepository.deleteById(id);
  }
}
