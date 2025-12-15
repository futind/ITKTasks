package ru.itk.objectmapper.util.exception;

import java.util.UUID;

public abstract class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, String id) {
      super(entityName + " with id " + id + " was not found");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
