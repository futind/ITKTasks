package ru.itk.objectmapper.dto.order;

import ru.itk.objectmapper.dto.order.enumeration.OrderStatus;

public record UpdateOrderRequestDto(
        String address,

        OrderStatus status
) {
}
