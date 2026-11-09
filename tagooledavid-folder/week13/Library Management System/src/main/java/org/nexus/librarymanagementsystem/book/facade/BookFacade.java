package org.nexus.librarymanagementsystem.book.facade;

import org.nexus.librarymanagementsystem.book.dto.BookRequest;
import org.nexus.librarymanagementsystem.book.dto.BookResponse;
import org.nexus.librarymanagementsystem.book.mapper.BookMapper;
import org.nexus.librarymanagementsystem.book.model.Book;
import org.nexus.librarymanagementsystem.book.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookFacade {
  private final BookService bookService;
  private final BookMapper bookMapper;

  public BookFacade(BookService bookService, BookMapper bookMapper) {
    this.bookService = bookService;
    this.bookMapper = bookMapper;
  }

  @Transactional
  public BookResponse create(BookRequest request) {
    Book saved = bookService.save(bookMapper.toEntity(request));
    return bookMapper.toResponse(saved);
  }

  @Transactional(readOnly = true)
  public BookResponse findById(Long id) {
    return bookMapper.toResponse(bookService.findById(id));
  }

  @Transactional(readOnly = true)
  public Page<BookResponse> findPage(int page, int size, String sortBy) {
    return bookService.findPage(page, size, sortBy).map(bookMapper::toResponse);
  }

  @Transactional
  public BookResponse update(Long id, BookRequest request) {
    Book book = bookService.findById(id);
    book.setTitle(request.title());
    book.setAuthor(request.author());
    book.setIsbn(request.isbn());
    book.setTotalCopies(request.totalCopies());
    return bookMapper.toResponse(bookService.save(book));
  }

  @Transactional
  public void deleteById(Long id) {
    bookService.deleteById(id);
  }
}
