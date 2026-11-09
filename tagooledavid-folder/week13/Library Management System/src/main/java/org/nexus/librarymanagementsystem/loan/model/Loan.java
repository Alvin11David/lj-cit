package org.nexus.librarymanagementsystem.loan.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nexus.librarymanagementsystem.book.model.Book;
import org.nexus.librarymanagementsystem.member.model.Member;

@Entity
@Table(name = "loans")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  private Book book;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @Column(nullable = false, name = "due_date")
  private LocalDate dueDate;

  @Column(name = "returned_at")
  private LocalDate returnedAt;

  @Column(nullable = false, name = "created_at")
  private LocalDate createdAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDate.now();
    this.dueDate = createdAt.plusDays(3);
  }
}
