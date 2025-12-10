package ru.itk.pageablebooklibrary.publisher.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PublisherDto(
        @NotNull
        UUID id,

        @NotBlank
        String name,

        @NotBlank
        String country
) {
}
