package org.nexus.librarymanagementsystem.book.repository;

import org.nexus.librarymanagementsystem.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}
