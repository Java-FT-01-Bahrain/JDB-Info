package com.example.bookapi.service;

import com.example.bookapi.exception.InformationExistException;
import com.example.bookapi.exception.InformationNotFoundException;
import com.example.bookapi.model.Book;
import com.example.bookapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }


    public Optional<Book> updateBook(Long bookId, Book bookObject) throws InformationNotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            book.get().setName(bookObject.getName());
            book.get().setDescription(bookObject.getDescription());
            bookRepository.save(book.get());
            return book;
        } else {
            throw new InformationNotFoundException("book with id " + bookId + " not found");
        }
    }

    public Optional<Book> deleteBook(Long bookId) throws InformationNotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            bookRepository.deleteById(bookId);
            return book;
        } else {
            throw new InformationNotFoundException("book with id " + bookId + " not found");
        }
    }
}
