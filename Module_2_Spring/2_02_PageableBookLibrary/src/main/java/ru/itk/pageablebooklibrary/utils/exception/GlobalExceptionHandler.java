package ru.itk.pageablebooklibrary.utils.exception;

import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = LibraryEntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLibraryEntityNotFoundException(Throwable ex, WebRequest request) {
        String path = request.getDescription(false).replaceFirst("^uri=", "");
        String type = (path + '/' + ex.getClass().getSimpleName()).toLowerCase();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
                                .type(URI.create(type))
                                .instance(URI.create(path))
                                .build()
                );
    }

    @ExceptionHandler(value = {LibraryEntityDeletionConflictException.class, LibraryEntityDuplicationException.class})
    public ResponseEntity<ErrorResponse> handleLibraryEntityForbiddenException(Throwable ex, WebRequest request) {
        String path = request.getDescription(false).replaceFirst("^uri=", "");
        String type = (path + '/' + ex.getClass().getSimpleName()).toLowerCase();

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(
                        ErrorResponse.builder(ex, HttpStatus.FORBIDDEN, ex.getMessage())
                                .type(URI.create(type))
                                .instance(URI.create(path))
                                .build()
                );
    }

    @ExceptionHandler(value = {PropertyReferenceException.class})
    public ResponseEntity<ErrorResponse> handlePropertyReferenceException(Throwable ex, WebRequest request) {
        String path = request.getDescription(false).replaceFirst("^uri=", "");
        String type = (path + '/' + ex.getClass().getSimpleName()).toLowerCase();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, ex.getMessage())
                                .type(URI.create(type))
                                .instance(URI.create(path))
                                .build()
                );
    }
}
