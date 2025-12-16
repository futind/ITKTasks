package ru.itk.objectmapper.dto.product;

import java.math.BigDecimal;

public record UpdateProductRequestDto(
        String name,

        String description,

        BigDecimal price,

        Integer quantityInStock
) {}
