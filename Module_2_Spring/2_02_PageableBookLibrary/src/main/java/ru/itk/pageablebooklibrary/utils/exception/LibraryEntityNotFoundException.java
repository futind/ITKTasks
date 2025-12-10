package ru.itk.pageablebooklibrary.utils.exception;

public class LibraryEntityNotFoundException extends RuntimeException {

    public LibraryEntityNotFoundException() {
        super("Entity was not found!");
    }

    public LibraryEntityNotFoundException(String message) {
        super(message);
    }
}
