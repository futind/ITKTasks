package ru.itk.pageablebooklibrary.author.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AuthorDto(
        @NotNull
        UUID id,

        @NotBlank
        String fullName
) {}
