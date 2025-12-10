package ru.itk.jdbcbooklibrary.model;

public record UpdateBookDto(
        String title,
        String author,
        Integer yearOfPublication
) {}
