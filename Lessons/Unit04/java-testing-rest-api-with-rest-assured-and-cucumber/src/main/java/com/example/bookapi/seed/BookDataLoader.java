package com.example.bookapi.seed;

import com.example.bookapi.model.Book;
import com.example.bookapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BookDataLoader implements CommandLineRunner {

    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        if (bookRepository.count() == 0) {
            Book book1 = new Book("Intro to Java Programming", "Some description");
            Book book2 = new Book("Core Java Volume I – Fundamentals", "Some description");
            Book book3 = new Book("Effective Java", "Some description");
            Book book4 = new Book("Java: A Beginner’s Guide", "Some description");
            Book book5 = new Book("Java - The Complete Reference", "Some description");
            bookRepository.save(book1);
            bookRepository.save(book2);
            bookRepository.save(book3);
            bookRepository.save(book4);
            bookRepository.save(book5);
        }
    }
}
