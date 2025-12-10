package ru.itk.jdbcbooklibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.itk.jdbcbooklibrary.controller.BookController;

import java.net.URI;

@ControllerAdvice(assignableTypes = BookController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNotFoundException(Throwable ex, WebRequest request) {
        String path = request.getDescription(false).replaceFirst("^uri=", "");
        String type = path + '/' + ex.getClass().getSimpleName();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
                                .instance(URI.create(path))
                                .type(URI.create(type))
                                .build()
                );
    }
}
