package com.marvambi.interbooks.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
