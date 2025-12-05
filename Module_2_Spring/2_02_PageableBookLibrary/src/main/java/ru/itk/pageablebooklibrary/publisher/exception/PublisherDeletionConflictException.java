package ru.itk.pageablebooklibrary.publisher.exception;

import ru.itk.pageablebooklibrary.utils.exception.LibraryEntityDeletionConflictException;

import java.util.UUID;

public class PublisherDeletionConflictException extends LibraryEntityDeletionConflictException {
    public PublisherDeletionConflictException(UUID publisherId) {
        super("Can't delete publisher with id " + publisherId + " - author has dependent entities. Use \"force\" parameter to delete regardless");
    }
}
