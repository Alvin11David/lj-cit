package org.nexus.librarymanagementsystem.loan.service;

import java.util.List;
import org.nexus.librarymanagementsystem.common.exception.LoanNotFoundException;
import org.nexus.librarymanagementsystem.loan.model.Loan;
import org.nexus.librarymanagementsystem.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
  private final LoanRepository loanRepository;

  public LoanService(LoanRepository loanRepository) {
    this.loanRepository = loanRepository;
  }

  public long countActiveLoansForBook(Long bookId) {
    return loanRepository.countByBookIdAndReturnedAtIsNull(bookId);
  }

  public Loan save(Loan loan) {
    return loanRepository.save(loan);
  }

  public Loan findById(Long id) {
    return loanRepository
        .findById(id)
        .orElseThrow(() -> new LoanNotFoundException("No loan found with id " + id));
  }

  public List<Loan> findAllWithBookAndMember() {
    return loanRepository.findAllWithBookAndMember();
  }

  public List<Loan> findAll() {
    return loanRepository.findAll();
  }
}
