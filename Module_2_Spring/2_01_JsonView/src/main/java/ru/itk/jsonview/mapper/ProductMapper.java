package ru.itk.jsonview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itk.jsonview.dto.product.ProductDto;
import ru.itk.jsonview.model.product.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(ProductEntity productEntity);

    @Mapping(target = "id", ignore = true)
    ProductEntity toEntity(ProductDto productDto);
}
