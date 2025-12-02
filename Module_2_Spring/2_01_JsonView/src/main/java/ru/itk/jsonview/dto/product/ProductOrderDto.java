package ru.itk.jsonview.dto.product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductOrderDto(
        UUID productId,
        BigDecimal price,
        Integer quantity
) {}
