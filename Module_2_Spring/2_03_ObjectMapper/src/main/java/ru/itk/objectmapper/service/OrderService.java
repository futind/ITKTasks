package ru.itk.objectmapper.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itk.objectmapper.dto.order.CreateOrderRequestDto;
import ru.itk.objectmapper.dto.order.OrderDto;
import ru.itk.objectmapper.dto.order.UpdateOrderRequestDto;
import ru.itk.objectmapper.dto.order.enumeration.OrderStatus;
import ru.itk.objectmapper.mapper.OrderMapper;
import ru.itk.objectmapper.model.OrderEntity;
import ru.itk.objectmapper.model.OrderItemEntity;
import ru.itk.objectmapper.repository.CustomerRepository;
import ru.itk.objectmapper.repository.OrderRepository;
import ru.itk.objectmapper.util.exception.CustomerNotFoundException;
import ru.itk.objectmapper.util.exception.OrderNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final OrderItemService orderItemService;

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    public OrderDto getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toDto)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Transactional
    public OrderDto createOrder(CreateOrderRequestDto createRequest) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setCustomer(
                customerRepository.findById(createRequest.customerId())
                        .orElseThrow(() -> new CustomerNotFoundException(createRequest.customerId()))
        );
        orderEntity.setOrderDate(LocalDate.now());
        orderEntity.setAddress(createRequest.address());

        List<OrderItemEntity> orderedItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for(CreateOrderRequestDto.OrderItemRequestDto orderItemRequest: createRequest.orderItems()) {
            OrderItemEntity orderItemEntity = orderItemService.createOrderItemEntity(orderItemRequest, orderEntity);
            orderedItems.add(orderItemEntity);
            totalPrice = totalPrice.add(
                    orderItemEntity.getPrice().multiply(new BigDecimal(orderItemRequest.quantity()))
            );
        }

        orderEntity.setTotalPrice(totalPrice);
        orderEntity.setProductOrders(orderedItems);
        orderEntity.setOrderStatus(OrderStatus.CREATED);

        orderRepository.save(orderEntity);
        return orderMapper.toDto(orderEntity);
    }

    @Transactional
    public OrderDto updateOrder(UUID orderId, UpdateOrderRequestDto updateRequest) {
        OrderEntity orderEntityToUpdate = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        orderMapper.updateOrder(orderEntityToUpdate, updateRequest);
        orderRepository.save(orderEntityToUpdate);
        return orderMapper.toDto(orderEntityToUpdate);
    }

    @Transactional
    public void deleteOrder(UUID orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderNotFoundException(orderId);
        }
        orderRepository.deleteById(orderId);
    }


}
