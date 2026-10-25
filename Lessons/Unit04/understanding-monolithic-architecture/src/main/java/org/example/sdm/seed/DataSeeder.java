package org.example.sdm.seed;

import org.example.sdm.model.Author;
import org.example.sdm.model.Book;
import org.example.sdm.repository.AuthorRepository;
import org.example.sdm.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public DataSeeder(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Clear existing data
        bookRepository.deleteAll();
        authorRepository.deleteAll();

        // Create authors
        Author author1 = new Author();
        author1.setName("George Orwell");
        author1.setBiography("George Orwell was an English novelist and essayist.");

        Author author2 = new Author();
        author2.setName("J.K. Rowling");
        author2.setBiography("J.K. Rowling is a British author, best known for the Harry Potter series.");

        // Save authors
        authorRepository.saveAll(Arrays.asList(author1, author2));

        // Create books
        Book book1 = new Book();
        book1.setTitle("1984");
        book1.setDescription("A dystopian novel set in a totalitarian society.");
        book1.setAuthor(author1);

        Book book2 = new Book();
        book2.setTitle("Animal Farm");
        book2.setDescription("A satirical allegory of Soviet totalitarianism.");
        book2.setAuthor(author1);

        Book book3 = new Book();
        book3.setTitle("Harry Potter and the Philosopher's Stone");
        book3.setDescription("The first book in the Harry Potter series.");
        book3.setAuthor(author2);

        // Save books
        bookRepository.saveAll(Arrays.asList(book1, book2, book3));

        // Print out the seeded data
        System.out.println("Seeded Authors: ");
        authorRepository.findAll().forEach(System.out::println);

        System.out.println("Seeded Books: ");
        bookRepository.findAll().forEach(System.out::println);
    }
}
