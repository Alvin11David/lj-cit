package org.nexus.librarymanagementsystem.loan.repository;

import java.util.List;
import org.nexus.librarymanagementsystem.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
  long countByBookIdAndReturnedAtIsNull(Long bookId);

  @Query("SELECT ln FROM Loan ln JOIN FETCH ln.book JOIN FETCH ln.member")
  List<Loan> findAllWithBookAndMember();
}
