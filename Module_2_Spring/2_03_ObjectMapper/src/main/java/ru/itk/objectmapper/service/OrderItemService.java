package ru.itk.objectmapper.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.itk.objectmapper.dto.order.CreateOrderRequestDto;
import ru.itk.objectmapper.model.OrderEntity;
import ru.itk.objectmapper.model.OrderItemEntity;
import ru.itk.objectmapper.model.ProductEntity;
import ru.itk.objectmapper.repository.ProductRepository;
import ru.itk.objectmapper.util.exception.NotEnoughProductInStock;
import ru.itk.objectmapper.util.exception.ProductNotFoundException;

@AllArgsConstructor
@Service
public class OrderItemService {

    private final ProductRepository productRepository;

    @Transactional(propagation = Propagation.MANDATORY)
    public OrderItemEntity createOrderItemEntity(CreateOrderRequestDto.OrderItemRequestDto request, OrderEntity orderEntity) {
        ProductEntity productEntity = productRepository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException(request.productId()));

        if (productEntity.getQuantityInStock() < request.quantity()) {
            throw new NotEnoughProductInStock(request.productId());
        }

        productEntity.setQuantityInStock(productEntity.getQuantityInStock() - request.quantity());

        return OrderItemEntity.builder()
                .order(orderEntity)
                .product(productEntity)
                .price(productEntity.getPrice())
                .quantity(request.quantity())
                .build();
    }
}
