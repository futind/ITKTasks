package ru.itk.jdbcbooklibrary.model;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BookDto(
        @NotNull
        UUID id,
        @NotNull
        String title,
        @NotNull
        String author,
        @NotNull
        Integer yearOfPublication
) {}
