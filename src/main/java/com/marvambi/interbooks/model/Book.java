package com.marvambi.interbooks.model;

import java.time.LocalDateTime;

public record Book(
    Integer id,
    String title,
    String author,
    Integer ISBN,
    Genre genre,
    LocalDateTime dateCreated,
    LocalDateTime dateUpdated
) {
    
}
