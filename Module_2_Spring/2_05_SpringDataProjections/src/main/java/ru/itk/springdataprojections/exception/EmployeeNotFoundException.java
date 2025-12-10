package ru.itk.springdataprojections.exception;

import java.util.UUID;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(UUID id) {
        super("Employee with id " + id + " was not found");
    }
}
