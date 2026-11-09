package org.nexus.librarymanagementsystem.loan.mapper;

import org.nexus.librarymanagementsystem.book.model.Book;
import org.nexus.librarymanagementsystem.loan.dto.LoanResponse;
import org.nexus.librarymanagementsystem.loan.model.Loan;
import org.nexus.librarymanagementsystem.member.model.Member;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {
  public Loan toEntity(Book book, Member member) {
    Loan loan = new Loan();
    loan.setBook(book);
    loan.setMember(member);
    return loan;
  }

  public LoanResponse toResponse(Loan loan) {
    return new LoanResponse(
        loan.getId(),
        loan.getBook().getId(),
        loan.getMember().getId(),
        loan.getDueDate(),
        loan.getReturnedAt(),
        loan.getCreatedAt());
  }
}
