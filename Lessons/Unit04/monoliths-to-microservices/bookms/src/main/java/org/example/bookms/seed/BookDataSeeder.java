package org.example.bookms.seed;

import org.example.bookms.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BookDataSeeder implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Autowired
    public BookDataSeeder(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO : Clear existing data
        // TODO : Create books
        // TODO : Save books
        // TODO : Print the saved books
    }
}
