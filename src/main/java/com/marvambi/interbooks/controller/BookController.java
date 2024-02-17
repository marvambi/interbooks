package com.marvambi.interbooks.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.marvambi.interbooks.model.Book;
import com.marvambi.interbooks.repository.BookCollectionRepository;

@RestController
@RequestMapping("/api/book")
public class BookController {
    
    private final BookCollectionRepository bookRepository;

    public BookController(BookCollectionRepository bookCollectionRepository) {
        this.bookRepository = bookCollectionRepository;
    }

    @GetMapping("")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Integer id) {
        return bookRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found!"));
    }
}
