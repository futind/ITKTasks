package ru.itk.pageablebooklibrary.utils.exception;

public class LibraryEntityDeletionConflictException extends RuntimeException {
    public LibraryEntityDeletionConflictException(String message) {
        super(message);
    }
}
