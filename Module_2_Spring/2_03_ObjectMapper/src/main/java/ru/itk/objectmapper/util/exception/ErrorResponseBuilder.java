package ru.itk.objectmapper.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;

public class ErrorResponseBuilder {

    public static ErrorResponse build(Throwable ex, HttpStatus status, WebRequest request) {
        String path = request.getDescription(false).replaceFirst("^uri=", "");
        String type = path + '/' + ex.getClass().getSimpleName();

        return ErrorResponse.builder(ex, status, ex.getMessage())
                .instance(URI.create(path))
                .type(URI.create(type))
                .build();
    }
}
