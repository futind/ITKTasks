package ru.itk.jsonview.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.itk.jsonview.dto.OrderDto;
import ru.itk.jsonview.dto.product.ProductDto;
import ru.itk.jsonview.dto.product.ProductOrderDto;
import ru.itk.jsonview.model.order.OrderEntity;
import ru.itk.jsonview.model.order.OrderStatus;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {

    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Test
    void toDto() {
        // arrange
        // setting up the data
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        OrderStatus orderStatus = OrderStatus.IN_PROGRESS;

        ProductDto product1 = new ProductDto(UUID.randomUUID(), "name1", "desc1", BigDecimal.ONE);
        ProductDto product2 = new ProductDto(UUID.randomUUID(), "name2", "desc2", BigDecimal.TEN);
        ProductOrderDto productOrder1 = new ProductOrderDto(product1.id(), product1.price(), 1);
        ProductOrderDto productOrder2 = new ProductOrderDto(product2.id(), product2.price(), 2);
        List<ProductOrderDto> productQuantities = List.of(productOrder1, productOrder2);

        // setting up the entity to map into a dto
        OrderEntity orderEntity = OrderEntity.builder()
                .id(orderId)
                .userId(userId)
                .status(orderStatus)
                .productQuantities(productQuantities)
                .build();

        // setting up the excepted result
        OrderDto expectedDto = new OrderDto(orderId, userId, orderStatus, productQuantities);

        // act
        OrderDto orderDto = orderMapper.toDto(orderEntity);

        // assert
        assertNotNull(orderDto);
        assertEquals(expectedDto, orderDto);
    }

    @Test
    void toEntity() {
        // arrange
        // setting up the data
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        OrderStatus orderStatus = OrderStatus.IN_PROGRESS;

        ProductDto product1 = new ProductDto(UUID.randomUUID(), "name1", "desc1", BigDecimal.ONE);
        ProductDto product2 = new ProductDto(UUID.randomUUID(), "name2", "desc2", BigDecimal.TEN);
        ProductOrderDto productOrder1 = new ProductOrderDto(product1.id(), product1.price(), 1);
        ProductOrderDto productOrder2 = new ProductOrderDto(product2.id(), product2.price(), 2);
        List<ProductOrderDto> productQuantities = List.of(productOrder1, productOrder2);

        // setting up the dto to map into an entity
        OrderDto orderDto = new OrderDto(orderId, userId, orderStatus, productQuantities);

        // setting up the excepted result
        OrderEntity expectedEntity = OrderEntity.builder()
                .userId(userId)
                .status(orderStatus)
                .productQuantities(productQuantities)
                .build();

        // act
        OrderEntity orderEntity = orderMapper.toEntity(orderDto);

        // assert
        assertNotNull(orderEntity);
        assertNull(orderEntity.getId());
        assertThat(orderEntity)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedEntity);
    }
}