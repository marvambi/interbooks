package com.marvambi.interbooks.repository;

import com.marvambi.interbooks.model.Book;
import com.marvambi.interbooks.model.Genre;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findByYearOfPub(Integer yearOfPub);

  List<Book> findByTitleContaining(String title);

List<Book> findByGenre(Genre genre);

Optional<Book> findByIsbn(String isbn);

List<Book> findByAuthor(String author);

List<Book> findByTitle(String title);
}