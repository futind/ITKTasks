package ru.itk.objectmapper.util.exception;

import java.util.UUID;

public class CustomerNotFoundException extends EntityNotFoundException {
    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(UUID id) {
        super("Customer", id.toString());
    }
}
