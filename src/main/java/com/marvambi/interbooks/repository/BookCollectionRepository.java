package com.marvambi.interbooks.repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.marvambi.interbooks.model.Book;


@Repository
public class BookCollectionRepository {
    private final List<Book> books = new ArrayList<>();

    public BookCollectionRepository() {}

    public List<Book> findAll() {
        return books;
    }

    public Optional<Book> findById(Integer id) {
        return books.stream().filter(b -> b.id().equals(id)).findFirst();
    }

    public Optional<Book> findByTitle(String title) {
        return books.stream().filter(b -> b.title().equals(title)).findFirst();
    }

    public Optional<Book> findByAuthor(String author) {
        return books.stream().filter(b -> b.author().equals(author)).collect(null);
    }

}
