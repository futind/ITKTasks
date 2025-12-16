package ru.itk.objectmapper.util.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import tools.jackson.core.JacksonException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static ResponseEntity<ErrorResponse> buildResponse(Throwable ex, WebRequest request, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(
                        ErrorResponseBuilder.build(ex, status, request)
                );
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(Throwable ex, WebRequest request) {
        return buildResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(Throwable ex, WebRequest request) {
        return buildResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotEnoughProductInStock.class)
    public ResponseEntity<ErrorResponse> handleNotEnoughProductInStock(Throwable ex, WebRequest request) {
        return buildResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = JacksonException.class)
    public ResponseEntity<ErrorResponse> handleJacksonException(Throwable ex, WebRequest request) {
        return buildResponse(ex, request, HttpStatus.BAD_REQUEST);
    }
}
