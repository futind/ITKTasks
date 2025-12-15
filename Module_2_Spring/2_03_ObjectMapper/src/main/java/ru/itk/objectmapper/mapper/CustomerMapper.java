package ru.itk.objectmapper.mapper;

import org.mapstruct.*;
import ru.itk.objectmapper.dto.customer.CreateCustomerRequestDto;
import ru.itk.objectmapper.dto.customer.CustomerDto;
import ru.itk.objectmapper.dto.customer.UpdateCustomerRequestDto;
import ru.itk.objectmapper.model.CustomerEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {

    CustomerDto toDto(CustomerEntity customerEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    CustomerEntity createCustomerEntity(CreateCustomerRequestDto request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerEntity(@MappingTarget CustomerEntity customerEntity,
                              UpdateCustomerRequestDto request);
}
