package ru.itk.jsonview.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.itk.jsonview.dto.product.ProductDto;
import ru.itk.jsonview.model.product.ProductEntity;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void toDto() {
        // arrange
        // setting up the data
        UUID productId = UUID.randomUUID();
        String productName = "name";
        String productDescription = "description";
        BigDecimal productPrice = BigDecimal.TEN;

        // setting up the entity to map into a dto
        ProductEntity productEntity = ProductEntity.builder()
                .id(productId)
                .name(productName)
                .description(productDescription)
                .price(productPrice)
                .build();

        // setting up the dto we expect from the method
        ProductDto expectedDto = new ProductDto(productId, productName, productDescription, productPrice);

        // act
        ProductDto productDto = productMapper.toDto(productEntity);

        // assert
        assertNotNull(productDto);
        assertEquals(expectedDto, productDto);
    }

    @Test
    void toEntity() {
        // arrange
        // setting up the data
        UUID productId = UUID.randomUUID();
        String productName = "name";
        String productDescription = "description";
        BigDecimal productPrice = BigDecimal.TEN;

        // setting up the entity we expect
        ProductEntity expectedEntity = ProductEntity.builder()
                .id(productId)
                .name(productName)
                .description(productDescription)
                .price(productPrice)
                .build();

        // setting up the dto we are going to map into an entity
        ProductDto productDto = new ProductDto(productId, productName, productDescription, productPrice);

        // act
        ProductEntity productEntity = productMapper.toEntity(productDto);

        // assert
        assertNotNull(productEntity);
        assertThat(productEntity)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedEntity);
    }
}