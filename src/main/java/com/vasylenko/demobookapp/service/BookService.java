package com.vasylenko.demobookapp.service;

import com.vasylenko.demobookapp.entity.Book;
import com.vasylenko.demobookapp.exception.BookNotFoundException;
import com.vasylenko.demobookapp.repos.BookRepository;
import com.vasylenko.demobookapp.request.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Long createNewBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setAuthor(bookRequest.getAuthor());
        book.setTitle(bookRequest.getTitle());
        book.setIsbn(bookRequest.getIsbn());

        book = bookRepository.save(book);
        return book.getId();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {

        Optional<Book> requestedBook = bookRepository.findById(id);

        if (requestedBook.isEmpty()) {
            throw new BookNotFoundException(String.format("Book with id '%s' not found", id));
        }
        return requestedBook.get();
    }

    public Book updateBook(Long id, BookRequest bookToUpdateRequest) {

        Optional<Book> bookFromDb = bookRepository.findById(id);

        if (bookFromDb.isEmpty()) {
            throw new BookNotFoundException(String.format("Book with id '%s' not found", id));
        }

        Book bookToUpdate = bookFromDb.get();
        bookToUpdate.setAuthor(bookToUpdateRequest.getAuthor());
        bookToUpdate.setTitle(bookToUpdateRequest.getTitle());
        bookToUpdate.setIsbn(bookToUpdateRequest.getIsbn());

        return bookToUpdate;
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
