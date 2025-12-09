package ru.itk.springdataprojections.dto.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.itk.springdataprojections.dto.employee.enumeration.EmployeePosition;

public record EmployeeProjectionDto(
        @NotBlank
        String fullName,

        @NotNull
        EmployeePosition position,

        @NotBlank
        String departmentName
) {
}
