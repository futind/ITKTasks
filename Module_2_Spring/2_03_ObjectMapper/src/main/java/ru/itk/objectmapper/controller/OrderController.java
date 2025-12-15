package ru.itk.objectmapper.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itk.objectmapper.dto.order.CreateOrderRequestDto;
import ru.itk.objectmapper.dto.order.UpdateOrderRequestDto;
import ru.itk.objectmapper.service.OrderService;
import ru.itk.objectmapper.util.validation.BeanValidator;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;
    private final BeanValidator beanValidator;

    @GetMapping
    public ResponseEntity<String> getAllOrders() {
        return ResponseEntity.ok(objectMapper.writeValueAsString(orderService.getAllOrders()));
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<String> getOrderById(@PathVariable UUID orderId) {
        return ResponseEntity.ok(objectMapper.writeValueAsString(orderService.getOrderById(orderId)));
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody String createOrderRequestDto) {
        CreateOrderRequestDto createRequest = objectMapper.readValue(createOrderRequestDto, CreateOrderRequestDto.class);
        beanValidator.validate(createRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(objectMapper.writeValueAsString(orderService.createOrder(createRequest)));
    }

    @PatchMapping(value = "/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable UUID orderId,
                                              @RequestBody String updateOrderRequestDto) {
        UpdateOrderRequestDto updateRequest = objectMapper.readValue(updateOrderRequestDto, UpdateOrderRequestDto.class);
        beanValidator.validate(updateRequest);
        return ResponseEntity.ok(objectMapper.writeValueAsString(orderService.updateOrder(orderId, updateRequest)));
    }

    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
