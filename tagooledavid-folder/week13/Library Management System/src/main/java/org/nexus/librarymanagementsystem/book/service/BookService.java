package org.nexus.librarymanagementsystem.book.service;

import java.util.List;
import org.nexus.librarymanagementsystem.book.model.Book;
import org.nexus.librarymanagementsystem.book.repository.BookRepository;
import org.nexus.librarymanagementsystem.common.exception.BookNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {
  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Book save(Book book) {
    return bookRepository.save(book);
  }

  public Page<Book> findPage(int page, int size, String sortBy) {
    Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy).ascending());
    return bookRepository.findAll(pageable);
  }

  public Book findById(Long id) {
    return bookRepository
        .findById(id)
        .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
  }

  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  public void deleteById(Long id) {
    if (!bookRepository.existsById(id)) {
      throw new BookNotFoundException("Book not found with id: " + id);
    }

    bookRepository.deleteById(id);
  }
}
