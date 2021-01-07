package com.vasylenko.demobookapp.cli;

import com.github.javafaker.Faker;
import com.vasylenko.demobookapp.entity.Book;
import com.vasylenko.demobookapp.repos.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class BookInitializr implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {

        log.info("Starting initialize simple data ...");

        Faker faker = new Faker();

        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setTitle(faker.book().title());
            book.setAuthor(faker.book().author());
            book.setIsbn(UUID.randomUUID().toString());

            bookRepository.save(book);
        }

        log.info("... finished with data initialization.");
    }
}
