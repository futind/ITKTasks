package ru.itk.jsonview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.itk.jsonview.dto.product.ProductOrderDto;
import ru.itk.jsonview.model.order.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderDto(
        UUID id,
        UUID userId,
        OrderStatus status,
        List<ProductOrderDto> productQuantities
) {
    @JsonProperty("totalPrice")
    public BigDecimal totalPrice() {
        return productQuantities.stream()
                .map(x -> x.price().multiply(BigDecimal.valueOf(x.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
