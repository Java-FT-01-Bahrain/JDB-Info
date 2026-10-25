package org.example.bookms.service;

import org.example.bookms.exception.InformationNotFoundException;
import org.example.bookms.model.AuthorDTO;
import org.example.bookms.model.Book;
import org.example.bookms.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    // TODO 1: Create a field of type AuthorServiceClient and name it authorServiceClient.

    // TODO 2: Inject the AuthorServiceClient bean using the @Autowired annotation.
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new InformationNotFoundException("Book with ID " + id + " not found."));
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new InformationNotFoundException("Book with ID " + id + " not found.");
        }
        bookRepository.deleteById(id);
    }

    // TODO 3: Implement the saveBook method
    // 1. Fetch the AuthorDTO object by calling the authorServiceClient's getAuthorById method using the author's ID from the book.
    // 2. Set the fetched AuthorDTO to the book object.
    // 3. Save the updated book object using the bookRepository's save method and return the saved book.
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> findBookByTitleAndAuthorName(String title, String authorName) {
        return bookRepository.findByTitleAndAuthor_Name(title, authorName);
    }
}
