package ru.itk.pageablebooklibrary.book.exception;

import ru.itk.pageablebooklibrary.utils.exception.LibraryEntityDuplicationException;

public class BookDuplicationConflictException extends LibraryEntityDuplicationException {
    public BookDuplicationConflictException(String isbn) {
        super("Book with ISBN " + isbn + " already exists");
    }
}
