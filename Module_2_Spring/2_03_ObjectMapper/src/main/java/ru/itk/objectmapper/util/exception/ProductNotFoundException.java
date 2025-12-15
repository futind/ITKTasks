package ru.itk.objectmapper.util.exception;

import java.util.UUID;

public class ProductNotFoundException extends EntityNotFoundException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(UUID id) {
        super("Product", id.toString());
    }
}
