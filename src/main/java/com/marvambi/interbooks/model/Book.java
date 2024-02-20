package com.marvambi.interbooks.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "author")
	private String author;

	@Column(name = "isbn")
	private String isbn;

    @Column(name = "genre")
	private Genre genre;

    @Column(name = "yearOfPub")
    private Integer yearOfPub;

	public Book() {}

	public Book(String title, String author, String isbn, Genre genre, Integer yearOfPub) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
        this.genre = genre;
        this.yearOfPub = yearOfPub;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getYearOfPub() {
        return yearOfPub;
    }

    public void setYearOfPub(Integer yearOfPub) {
        this.yearOfPub = yearOfPub;
    }

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", yearOfPub=" + yearOfPub + ", genre=" + genre +"]";
	}
}
