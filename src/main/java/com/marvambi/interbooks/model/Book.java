package com.marvambi.interbooks.model;

import java.time.LocalDateTime;

public record Book(
    Integer id,
    String title,
    String author,
    Integer isbn,
    Genre genre,
    LocalDateTime yearOfPublication,
    LocalDateTime dateCreated,
    LocalDateTime dateUpdated
) {
    
}
