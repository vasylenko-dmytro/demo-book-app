package com.vasylenko.demobookapp.controller;

import com.vasylenko.demobookapp.entity.Book;
import com.vasylenko.demobookapp.request.BookRequest;
import com.vasylenko.demobookapp.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@Api(value = "book resources", description = "CRUD operations")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    @ApiOperation(value = "create new book", response = ResponseEntity.class)
    public ResponseEntity<Void> createNewBook(@Valid @RequestBody BookRequest bookRequest,
                                              UriComponentsBuilder uriComponentsBuilder) {

        Long id = bookService.createNewBook(bookRequest);
        UriComponents uriComponents = uriComponentsBuilder.path("/api/books/{id}").buildAndExpand(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "show all books", response = ResponseEntity.class)
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "show book by ID", response = ResponseEntity.class)
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update book", response = ResponseEntity.class)
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.updateBook(id, bookRequest));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete Book", response = ResponseEntity.class)
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }
}
