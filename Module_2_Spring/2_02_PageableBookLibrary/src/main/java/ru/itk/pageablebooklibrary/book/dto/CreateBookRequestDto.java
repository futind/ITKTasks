package ru.itk.pageablebooklibrary.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateBookRequestDto(
        String ISBN,

        @NotBlank
        String title,

        String summary,

        @NotNull
        UUID authorId,

        @NotNull
        UUID publisherId
) {}
