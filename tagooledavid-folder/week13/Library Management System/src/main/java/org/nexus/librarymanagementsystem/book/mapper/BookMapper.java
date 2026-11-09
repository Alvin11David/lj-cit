package org.nexus.librarymanagementsystem.book.mapper;

import org.nexus.librarymanagementsystem.book.dto.BookRequest;
import org.nexus.librarymanagementsystem.book.dto.BookResponse;
import org.nexus.librarymanagementsystem.book.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
  public Book toEntity(BookRequest bookRequest) {
    Book book = new Book();
    book.setTitle(bookRequest.title());
    book.setAuthor(bookRequest.author());
    book.setIsbn(bookRequest.isbn());
    book.setTotalCopies(bookRequest.totalCopies());

    return book;
  }

  public BookResponse toResponse(Book book) {
    return new BookResponse(
        book.getId(),
        book.getTitle(),
        book.getAuthor(),
        book.getIsbn(),
        book.getTotalCopies(),
        book.getCreatedAt());
  }
}
