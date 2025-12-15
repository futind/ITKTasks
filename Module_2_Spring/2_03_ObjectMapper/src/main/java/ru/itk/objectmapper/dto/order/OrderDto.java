package ru.itk.objectmapper.dto.order;

import ru.itk.objectmapper.dto.customer.CustomerDto;
import ru.itk.objectmapper.dto.order.enumeration.OrderStatus;
import ru.itk.objectmapper.dto.product.ProductDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record OrderDto(
        UUID id,

        CustomerDto customer,

        List<OrderItemDto> orderedItems,

        LocalDate orderDate,

        String address,

        BigDecimal totalPrice,

        OrderStatus orderStatus
) {
    public record OrderItemDto(

            ProductDto product,

            Integer quantity,

            BigDecimal price
    ) {}
}
