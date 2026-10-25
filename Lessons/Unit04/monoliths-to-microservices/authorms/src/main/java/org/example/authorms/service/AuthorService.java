package org.example.authorms.service;

import org.example.authorms.exception.InformationExistException;
import org.example.authorms.exception.InformationNotFoundException;
import org.example.authorms.model.Author;
import org.example.authorms.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(Author author) {
        Optional<Author> existingAuthor = authorRepository.findByName(author.getName());
        if (existingAuthor.isPresent()) {
            throw new InformationExistException("Author with name " + author.getName() + " already exists.");
        }
        return authorRepository.save(author);
    }

    public Author findAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new InformationNotFoundException("Author with ID " + id + " not found."));
    }

    public Author findAuthorByName(String name) {
        return authorRepository.findByName(name)
                .orElseThrow(() -> new InformationNotFoundException("Author with name " + name + " not found."));
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new InformationNotFoundException("Author with ID " + id + " not found.");
        }
        authorRepository.deleteById(id);
    }
}

