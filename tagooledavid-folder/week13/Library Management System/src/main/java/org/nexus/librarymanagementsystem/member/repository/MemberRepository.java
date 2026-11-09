package org.nexus.librarymanagementsystem.member.repository;

import java.util.Optional;
import org.nexus.librarymanagementsystem.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByEmail(String email);
}
