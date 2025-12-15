package ru.itk.objectmapper.mapper;

import org.mapstruct.*;
import ru.itk.objectmapper.dto.product.CreateProductRequestDto;
import ru.itk.objectmapper.dto.product.ProductDto;
import ru.itk.objectmapper.dto.product.UpdateProductRequestDto;
import ru.itk.objectmapper.model.ProductEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    ProductDto toDto(ProductEntity productEntity);

    @Mapping(target = "id", ignore = true)
    ProductEntity createProductEntity(CreateProductRequestDto request);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductEntity(@MappingTarget ProductEntity productEntity,
                             UpdateProductRequestDto request);
}
