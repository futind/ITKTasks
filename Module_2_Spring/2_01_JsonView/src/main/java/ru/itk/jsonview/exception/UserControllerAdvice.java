package ru.itk.jsonview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.itk.jsonview.controller.UserController;

import java.net.URI;

@ControllerAdvice(assignableTypes = UserController.class)
public class UserControllerAdvice {

    /**
     * Handler of NOT_FOUND exceptions
     * @param ex - exception caught
     * @param request - web request in which the exception was caught
     * @return {@link ErrorResponse} which describes what happened
     */
    @ExceptionHandler(value = UserNotFoundException.class)
    ResponseEntity<ErrorResponse> handleNotFoundException(Throwable ex, WebRequest request) {
        String path = request.getDescription(false).replaceFirst("^uri=", "");
        String type = (path + '/' + ex.getClass().getSimpleName().toLowerCase());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
                        .type(URI.create(type))
                        .instance(URI.create(path))
                        .build()
                );
    }
}
