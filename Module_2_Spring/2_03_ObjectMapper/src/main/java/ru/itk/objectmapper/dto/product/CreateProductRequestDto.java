package ru.itk.objectmapper.dto.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;


public record CreateProductRequestDto(
    @NotBlank
    String name,

    String description,

    @NotNull
    @DecimalMin("0.0")
    BigDecimal price,

    @NotNull
    @Min(0)
    Integer quantityInStock
) {}
