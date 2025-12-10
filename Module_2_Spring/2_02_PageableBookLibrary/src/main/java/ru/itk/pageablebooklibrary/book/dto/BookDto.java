package ru.itk.pageablebooklibrary.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.itk.pageablebooklibrary.author.dto.AuthorDto;
import ru.itk.pageablebooklibrary.publisher.dto.PublisherDto;

import java.util.UUID;

public record BookDto(
        @NotNull
        UUID id,

        @NotBlank
        String ISBN,

        @NotBlank
        String title,

        String summary,

        @NotNull
        AuthorDto author,

        @NotNull
        PublisherDto publisher
) {}
