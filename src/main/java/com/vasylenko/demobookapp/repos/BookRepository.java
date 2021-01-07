package com.vasylenko.demobookapp.repos;

import com.vasylenko.demobookapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
