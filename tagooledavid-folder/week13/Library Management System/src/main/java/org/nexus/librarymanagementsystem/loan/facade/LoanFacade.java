package org.nexus.librarymanagementsystem.loan.facade;

import java.time.LocalDate;
import java.util.List;
import org.nexus.librarymanagementsystem.book.model.Book;
import org.nexus.librarymanagementsystem.book.service.BookService;
import org.nexus.librarymanagementsystem.common.exception.ConflictException;
import org.nexus.librarymanagementsystem.loan.dto.LoanRequest;
import org.nexus.librarymanagementsystem.loan.dto.LoanResponse;
import org.nexus.librarymanagementsystem.loan.mapper.LoanMapper;
import org.nexus.librarymanagementsystem.loan.model.Loan;
import org.nexus.librarymanagementsystem.loan.service.LoanService;
import org.nexus.librarymanagementsystem.member.model.Member;
import org.nexus.librarymanagementsystem.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanFacade {
  private final LoanService loanService;
  private final BookService bookService;
  private final MemberService memberService;
  private final LoanMapper loanMapper;

  public LoanFacade(
      LoanService loanService,
      BookService bookService,
      MemberService memberService,
      LoanMapper loanMapper) {
    this.loanService = loanService;
    this.bookService = bookService;
    this.memberService = memberService;
    this.loanMapper = loanMapper;
  }

  @Transactional
  public LoanResponse create(LoanRequest request) {

    Book book = bookService.findById(request.bookId());
    Member member = memberService.findById(request.memberId());

    long activeLoans = loanService.countActiveLoansForBook(book.getId());
    if (activeLoans >= book.getTotalCopies()) {
      throw new ConflictException("No available copies for book id " + book.getId());
    }

    Loan loan = loanMapper.toEntity(book, member);
    Loan saved = loanService.save(loan);
    return loanMapper.toResponse(saved);
  }

  @Transactional(readOnly = true)
  public LoanResponse findById(Long id) {
    return loanMapper.toResponse(loanService.findById(id));
  }

  @Transactional(readOnly = true)
  public List<LoanResponse> findAll() {
    return loanService.findAllWithBookAndMember().stream().map(loanMapper::toResponse).toList();
  }

  @Transactional
  public LoanResponse returnLoan(Long id) {
    Loan loan = loanService.findById(id);
    loan.setReturnedAt(LocalDate.now());
    return loanMapper.toResponse(loanService.save(loan));
  }
}
