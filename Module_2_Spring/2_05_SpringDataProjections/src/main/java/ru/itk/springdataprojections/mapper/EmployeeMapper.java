package ru.itk.springdataprojections.mapper;

import org.mapstruct.*;
import ru.itk.springdataprojections.dto.employee.CreateEmployeeRequestDto;
import ru.itk.springdataprojections.dto.employee.EmployeeDto;
import ru.itk.springdataprojections.dto.employee.EmployeeProjectionDto;
import ru.itk.springdataprojections.dto.employee.UpdateEmployeeRequestDto;
import ru.itk.springdataprojections.model.EmployeeEntity;
import ru.itk.springdataprojections.projection.EmployeeProjection;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {DepartmentMapper.class})
public interface EmployeeMapper {

    EmployeeDto toDto(EmployeeEntity employeeEntity);

    EmployeeProjectionDto toProjectionDto(EmployeeProjection employeeProjection);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    EmployeeEntity createEmployee(CreateEmployeeRequestDto createEmployeeRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEmployee(@MappingTarget EmployeeEntity employeeEntity,
                                  UpdateEmployeeRequestDto updateEmployeeRequestDto);
}
