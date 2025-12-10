package ru.itk.pageablebooklibrary.book.exception;

import ru.itk.pageablebooklibrary.utils.exception.LibraryEntityNotFoundException;

import java.util.UUID;

public class BookNotFoundException extends LibraryEntityNotFoundException {

    public BookNotFoundException(UUID bookId) {
        super("Could not find Book with ID " + bookId);
    }

    public BookNotFoundException(String ISBN) {
        super("Could not find Book with ISBN " + ISBN);
    }
}
