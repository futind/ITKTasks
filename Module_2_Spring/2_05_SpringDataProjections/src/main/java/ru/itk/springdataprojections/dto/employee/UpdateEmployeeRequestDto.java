package ru.itk.springdataprojections.dto.employee;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import ru.itk.springdataprojections.dto.employee.enumeration.EmployeePosition;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateEmployeeRequestDto(
        String firstName,

        String lastName,

        EmployeePosition position,

        @DecimalMin("0.0")
        BigDecimal salary,

        UUID departmentId
) {
}
