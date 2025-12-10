package ru.itk.springdataprojections.exception;

import java.util.UUID;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(UUID id) {
        super("Department with id " + id + " was not found");
    }
}
