package ru.itk.objectmapper.dto.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequestDto(
        @NotNull
        UUID customerId,

        @NotBlank
        String address,

        @NotEmpty
        @Valid
        List<OrderItemRequestDto> orderItems
) {
        public record OrderItemRequestDto(
                @NotNull
                UUID productId,

                @NotNull
                @Positive
                Integer quantity
        ) {}
}