package ru.itk.springdataprojections.projection;

import ru.itk.springdataprojections.dto.employee.enumeration.EmployeePosition;

public interface EmployeeProjection {

    String getFullName();

    EmployeePosition getPosition();

    String getDepartmentName();
}
