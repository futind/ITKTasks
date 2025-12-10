package ru.itk.pageablebooklibrary.author.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateAuthorRequestDto(
    @NotBlank
    String fullName
) {}
