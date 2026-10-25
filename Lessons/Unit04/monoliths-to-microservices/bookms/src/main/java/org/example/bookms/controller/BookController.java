package org.example.bookms.controller;

import org.example.bookms.model.Book;
import org.example.bookms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    // TODO 1: Create a field of type AuthorServiceClient

    // TODO 2: Inject AuthorServiceClient using constructor injection
    @Autowired
    public BookController(BookService bookService,) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Long authorId = book.getAuthor().getAuthorId();
        // TODO 3: Call the getAuthorById method of the AuthorServiceClient and pass the authorId as an argument.
        // TODO 4: Set the AuthorDTO object returned by the getAuthorById method to the author field of the book object.
        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.ok(savedBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAllBooks();
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Book> getBookByTitleAndAuthor(@RequestParam String title, @RequestParam String authorName) {
        Optional<Book> book = bookService.findBookByTitleAndAuthorName(title, authorName);
        return book.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
