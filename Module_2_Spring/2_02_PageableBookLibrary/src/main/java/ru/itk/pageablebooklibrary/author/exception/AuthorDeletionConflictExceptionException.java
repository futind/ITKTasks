package ru.itk.pageablebooklibrary.author.exception;

import ru.itk.pageablebooklibrary.utils.exception.LibraryEntityDeletionConflictException;

import java.util.UUID;

public class AuthorDeletionConflictExceptionException extends LibraryEntityDeletionConflictException {
    public AuthorDeletionConflictExceptionException(UUID authorId) {
        super("Can't delete author with id " + authorId + " - author has dependent entities. Use \"force\" parameter to delete regardless");
    }
}
