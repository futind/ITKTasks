package ru.itk.jsonview.dto;

import ru.itk.jsonview.model.order.OrderStatus;

import java.util.Map;
import java.util.UUID;

public record OrderDto(UUID id, OrderStatus status, Map<UUID, Integer> productQuantity) {}
