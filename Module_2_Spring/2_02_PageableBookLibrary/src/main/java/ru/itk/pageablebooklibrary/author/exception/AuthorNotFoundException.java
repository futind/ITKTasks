package ru.itk.pageablebooklibrary.author.exception;

import ru.itk.pageablebooklibrary.utils.exception.LibraryEntityNotFoundException;

import java.util.UUID;

public class AuthorNotFoundException extends LibraryEntityNotFoundException {

    public AuthorNotFoundException(UUID authorId) {
        super("Author with id " + authorId + " was not found");
    }
}
