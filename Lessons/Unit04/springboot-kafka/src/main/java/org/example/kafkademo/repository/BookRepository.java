package org.example.kafkademo.repository;

import org.example.kafkademo.model.Author;
import org.example.kafkademo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleAndAuthor(String title, Author author);
}

