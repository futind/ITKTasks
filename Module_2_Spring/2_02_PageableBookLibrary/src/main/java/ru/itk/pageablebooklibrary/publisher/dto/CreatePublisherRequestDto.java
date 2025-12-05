package ru.itk.pageablebooklibrary.publisher.dto;

import jakarta.validation.constraints.NotBlank;

public record CreatePublisherRequestDto(
        @NotBlank
        String name,
        @NotBlank
        String country
) {
}
