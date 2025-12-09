package ru.itk.springdataprojections.mapper;

import org.mapstruct.*;
import ru.itk.springdataprojections.dto.department.CreateDepartmentRequestDto;
import ru.itk.springdataprojections.dto.department.DepartmentDto;
import ru.itk.springdataprojections.dto.department.UpdateDepartmentRequestDto;
import ru.itk.springdataprojections.model.DepartmentEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DepartmentMapper {

    DepartmentDto toDto(DepartmentEntity departmentEntity);

    @Mapping(target = "id", ignore = true)
    DepartmentEntity createDepartment(CreateDepartmentRequestDto createDepartmentRequestDto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDepartment(@MappingTarget DepartmentEntity departmentEntity,
                          UpdateDepartmentRequestDto updateDepartmentRequestDto);
}
