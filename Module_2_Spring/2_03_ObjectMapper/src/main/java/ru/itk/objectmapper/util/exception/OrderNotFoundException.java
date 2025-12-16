package ru.itk.objectmapper.util.exception;

import java.util.UUID;

public class OrderNotFoundException extends EntityNotFoundException {

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(UUID id) {
        super("Order", id.toString());
    }
}
