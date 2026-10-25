package com.example.bookapi.controller;

import com.example.bookapi.exception.InformationNotFoundException;
import com.example.bookapi.model.Book;
import com.example.bookapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/hello-world/")
    public String helloWorld() {
        return "hello World";
    }

    static HashMap<String, Object> message = new HashMap<>();

    @GetMapping(path = "/books/")
    public ResponseEntity<?> getAllBooks() {
        List<Book> bookList = bookService.getAllBooks();
        if (bookList.isEmpty()) {
            message.put("message", "cannot find any books ");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            message.put("message", "success");
            message.put("data", bookList);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable(value = "bookId") Long bookId) {
        Optional<Book> book = bookService.getBookById(bookId);
        if (book.isPresent()) {
            message.put("message", "success");
            message.put("data", book.get());
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "cannot find book with id " + bookId);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/books/")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        Book newBook = bookService.createBook(book);
        if (newBook != null) {
            message.put("message", "success");
            message.put("data", newBook);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message.put("message", "unable to create a book at this time");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable(value = "bookId") Long bookId, @RequestBody Book bookObject) throws InformationNotFoundException {
        Optional<Book> bookToUpdate = bookService.updateBook(bookId, bookObject);
        if (bookToUpdate.isEmpty()) {
            message.put("message", "cannot find book with id " + bookId);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            message.put("message", "book with id " + bookId + " has been successfully updated");
            message.put("data", bookToUpdate.get());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "bookId") Long bookId) {
        Optional<Book> bookToDelete = bookService.deleteBook(bookId);
        if (bookToDelete.isEmpty()) {
            message.put("message", "cannot find book with id " + bookId);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            message.put("message", "book with id " + bookId + " has been successfully deleted");
            message.put("data", bookToDelete.get());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}