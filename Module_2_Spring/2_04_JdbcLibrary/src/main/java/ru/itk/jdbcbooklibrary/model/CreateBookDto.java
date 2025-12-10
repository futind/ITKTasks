package ru.itk.jdbcbooklibrary.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateBookDto(
        @NotNull
        String title,

        @NotNull
        String author,

        @NotNull
        @Min(1)
        Integer yearOfPublication
) {
}
