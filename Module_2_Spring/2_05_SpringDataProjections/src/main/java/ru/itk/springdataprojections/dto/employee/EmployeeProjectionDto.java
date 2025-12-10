package ru.itk.springdataprojections.dto.employee;

import ru.itk.springdataprojections.dto.employee.enumeration.EmployeePosition;

public record EmployeeProjectionDto(
        String fullName,

        EmployeePosition position,

        String departmentName
) {
}
