package ru.itk.jsonview.exception;

import java.util.UUID;

/**
 * An exception in case there was no user with provided UUID
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(UUID userId) {
        super("User with id " + userId + " not found");
    }
}
