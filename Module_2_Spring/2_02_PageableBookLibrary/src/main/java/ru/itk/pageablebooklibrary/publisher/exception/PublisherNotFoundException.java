package ru.itk.pageablebooklibrary.publisher.exception;

import ru.itk.pageablebooklibrary.utils.exception.LibraryEntityNotFoundException;

import java.util.UUID;

public class PublisherNotFoundException extends LibraryEntityNotFoundException {
    public PublisherNotFoundException(UUID publisherId) {
        super("Could not find publisher with id " + publisherId);
    }
}
