package ru.itk.springdataprojections.dto.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DepartmentDto(
        @NotNull
        UUID id,

        @NotBlank
        String name
) {}
