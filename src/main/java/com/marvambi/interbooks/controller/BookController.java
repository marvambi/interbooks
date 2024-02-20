package com.marvambi.interbooks.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvambi.interbooks.model.Book;
import com.marvambi.interbooks.model.Genre;
import com.marvambi.interbooks.repository.BookRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@GetMapping("")
	public ResponseEntity<List<Book>> getAllbooks() {
		try {
			List<Book> books = new ArrayList<>();

            bookRepository.findAll().forEach(books::add);
			
			if (books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
    		headers.add("SERVER ERROR", "INTERNAL SERVER ERROR");
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @GetMapping("/isbn/{isbn}")
	public ResponseEntity<Book> getBookByIsbn(@PathVariable("isbn") String isbn) {
		Optional<Book> bookData = bookRepository.findByIsbn(isbn);

		if (bookData.isPresent()) {
			return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    @PostMapping("/add")
	public ResponseEntity<Book> createEmployee(@RequestBody Book book) {
		try {
			Book _book = bookRepository
					.save(new Book(book.getTitle(), book.getAuthor(), book.getIsbn(), book.getGenre(), book.getYearOfPub()));
			return new ResponseEntity<>(_book, HttpStatus.CREATED);
		} catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
    		headers.add("SERVER ERROR", "INTERNAL SERVER ERROR");
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PutMapping("/update/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
		Optional<Book> bookData = bookRepository.findById(id);

		if (bookData.isPresent()) {
			Book _book = bookData.get();
			_book.setTitle(book.getTitle());
            _book.setIsbn(book.getIsbn());
			_book.setAuthor(book.getAuthor());
			_book.setYearOfPub(book.getYearOfPub());
            _book.setGenre(book.getGenre());
			return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
		try {
			bookRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/all")
	public ResponseEntity<HttpStatus> deleteAllbooks() {
		try {
			bookRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/year/{year}")
	public ResponseEntity<List<Book>> getBookYear(@PathVariable("year") Integer year) {
		try {
			List<Book> books = bookRepository.findByYearOfPub(year);

			if (books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @GetMapping("/genre/{genre}")
	public ResponseEntity<List<Book>> findByGenre(@PathVariable("genre") Genre genre) {
		try {
			List<Book> books = bookRepository.findByGenre(genre);

			if (books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/author/{author}")
	public ResponseEntity<List<Book>> findByAuthor(@PathVariable("author") String author) {
		try {
			List<Book> books = bookRepository.findByAuthor(author);

			if (books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/title/{title}")
	public ResponseEntity<List<Book>> findByTitle(@PathVariable("title") String title) {
		try {
			List<Book> books = bookRepository.findByTitle(title);

			if (books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
