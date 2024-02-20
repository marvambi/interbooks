package com.marvambi.interbooks.repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.marvambi.interbooks.model.Book;
import com.marvambi.interbooks.model.Genre;

import jakarta.annotation.PostConstruct;


@Repository
public class BookCollectionRepository {
    private static final String MARVIN_AMBROSE = "Marvin Ambrose";
    private final List<Book> books = new ArrayList<>();

    public BookCollectionRepository() {}

    public List<Book> findAll() {
        return books;
    }

    public Optional<Book> findByIsbn(String isbn) {
        return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst();
    }

    public List<Book> findByTitle(String title) {
        return books.stream().filter(b -> b.getTitle().equals(title)).collect(Collectors.toList());
    }

    public List<Book> findByAuthor(String author) {
        return books.stream().filter(b -> b.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public List<Book> findByGenre(Genre genre) {
        return books.stream().filter(b -> b.getGenre().equals(genre)).collect(Collectors.toList());
    }

    public List<Book> findYearOfPub(Integer yearOfPub) {
        return books.stream().filter(b -> b.getYearOfPub().equals(yearOfPub)).collect(Collectors.toList());
    }




    @PostConstruct
    private void init() {
        Book book = new Book(
                "Mindful Technology",
                MARVIN_AMBROSE,
                "24445-1123-9877",
                Genre.HORROR,
                2023
                );

        Book book2 = new Book(
            "Mindful Technology II",
            MARVIN_AMBROSE,
            "76445-1123-9877",
            Genre.HORROR,
            2023
            );

        Book book5 = new Book(
            "Future Technology II",
            MARVIN_AMBROSE,
            "66445-1123-9877",
            Genre.HORROR,
            2013
            );

        

        books.add(book);
        books.add(book2);
        books.add(book5);

    }

}
