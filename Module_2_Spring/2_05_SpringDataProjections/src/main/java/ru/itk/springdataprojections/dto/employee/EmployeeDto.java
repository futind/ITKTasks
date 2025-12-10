package ru.itk.springdataprojections.dto.employee;

import jakarta.validation.constraints.DecimalMin;
import ru.itk.springdataprojections.dto.department.DepartmentDto;
import ru.itk.springdataprojections.dto.employee.enumeration.EmployeePosition;

import java.math.BigDecimal;
import java.util.UUID;

public record EmployeeDto(
        UUID id,

        String firstName,

        String lastName,

        EmployeePosition position,

        @DecimalMin("0.0")
        BigDecimal salary,

        DepartmentDto department
) {}
