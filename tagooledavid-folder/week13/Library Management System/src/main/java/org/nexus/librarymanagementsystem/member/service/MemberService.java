package org.nexus.librarymanagementsystem.member.service;

import java.util.List;
import org.nexus.librarymanagementsystem.common.exception.MemberNotFoundException;
import org.nexus.librarymanagementsystem.member.model.Member;
import org.nexus.librarymanagementsystem.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  public MemberService(MemberRepository memberRepository,PasswordEncoder passwordEncoder) {
    this.memberRepository = memberRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Member create(Member member){
    String password = member.getPassword();
    String encodedPassword=passwordEncoder.encode(password);
    member.setPassword(encodedPassword);
    return memberRepository.save(member);
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
