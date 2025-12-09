package ru.itk.springdataprojections.dto.department;

import jakarta.validation.constraints.NotBlank;

public record CreateDepartmentRequestDto(
        @NotBlank
        String name
) {}
