package ru.itk.jdbcbooklibrary.exception;

import java.util.UUID;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(UUID id) {
        super("Book with id " + id + " was not found");
    }
}
