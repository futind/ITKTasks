package ru.itk.objectmapper.dto.product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDto(
        UUID id,

        String name,

        String description,

        BigDecimal price,

        Integer quantityInStock
) {}
