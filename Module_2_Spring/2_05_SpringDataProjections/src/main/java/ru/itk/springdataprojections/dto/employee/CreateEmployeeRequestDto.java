package ru.itk.springdataprojections.dto.employee;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.itk.springdataprojections.dto.employee.enumeration.EmployeePosition;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateEmployeeRequestDto(
        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotNull
        EmployeePosition position,

        @NotNull
        @DecimalMin("0.0")
        BigDecimal salary,

        @NotNull
        UUID departmentId
) {}
