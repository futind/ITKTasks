package ru.itk.jsonview.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itk.jsonview.dto.OrderDto;
import ru.itk.jsonview.dto.product.ProductOrderDto;
import ru.itk.jsonview.dto.user.CreateUserRequestDto;
import ru.itk.jsonview.dto.user.UpdateUserRequestDto;
import ru.itk.jsonview.dto.user.UserDto;
import ru.itk.jsonview.model.order.OrderEntity;
import ru.itk.jsonview.model.order.OrderStatus;
import ru.itk.jsonview.model.user.UserEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @Mock
    private OrderMapper orderMapper;

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapperImpl(orderMapper);
    }
    
    @Test
    void toDto() {
        // arrange
        UUID userId = UUID.randomUUID();
        String fullName = "name";
        String email = "mail";
        OrderEntity orderEntity = new OrderEntity(
                UUID.randomUUID(),
                userId,
                OrderStatus.IN_PROGRESS,
                List.of(new ProductOrderDto(UUID.randomUUID(), BigDecimal.ONE, 1))
        );
        OrderDto orderDto = new OrderDto(
                orderEntity.getId(),
                orderEntity.getUserId(),
                orderEntity.getStatus(),
                orderEntity.getProductQuantities()
        );

        when(orderMapper.toDto(orderEntity)).thenReturn(orderDto);

        UserEntity userEntity = new UserEntity(userId, fullName, email, List.of(orderEntity));

        UserDto expectedDto = new UserDto(userId, fullName, email, List.of(orderMapper.toDto(orderEntity)));

        // act
        UserDto actualDto = userMapper.toDto(userEntity);

        // assert
        assertNotNull(actualDto);
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void toEntity() {
        // arrange
        UUID userId = UUID.randomUUID();
        String fullName = "name";
        String email = "mail";
        OrderEntity orderEntity = new OrderEntity(
                UUID.randomUUID(),
                userId,
                OrderStatus.IN_PROGRESS,
                List.of(new ProductOrderDto(UUID.randomUUID(), BigDecimal.ONE, 1))
        );
        UserEntity expectedEntity = new UserEntity(userId, fullName, email, List.of(orderEntity));
        OrderDto orderDto = new OrderDto(
                orderEntity.getId(),
                orderEntity.getUserId(),
                orderEntity.getStatus(),
                orderEntity.getProductQuantities()
        );

        when(orderMapper.toDto(orderEntity)).thenReturn(orderDto);
        when(orderMapper.toEntity(orderDto)).thenReturn(orderEntity);

        UserDto userDto = new UserDto(userId, fullName, email, List.of(orderMapper.toDto(orderEntity)));

        // act
        UserEntity actualEntity = userMapper.toEntity(userDto);

        // assert
        assertNotNull(actualEntity);
        assertNull(actualEntity.getId());
        assertThat(actualEntity)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedEntity);
    }

    @Test
    void createFromRequest() {
        // arrange
        String fullName = "name";
        String email = "mail";
        CreateUserRequestDto request = new CreateUserRequestDto(fullName, email);

        UserEntity expectedEntity = new UserEntity(null, fullName, email, null);

        // act
        UserEntity actualEntity = userMapper.createFromRequest(request);

        // assert
        assertNotNull(actualEntity);
        assertNull(actualEntity.getId());
        assertThat(actualEntity)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedEntity);
    }

    @Test
    void updateUserFromRequest() {
        // arrange
        String fullName = "name";
        String sentEmail = "mail";
        UUID userId = UUID.randomUUID();

        UpdateUserRequestDto request = new UpdateUserRequestDto(fullName, sentEmail);

        UserEntity expectedEntity = new UserEntity(userId, fullName, sentEmail, null);

        UserEntity targetEntity = new UserEntity(userId, "oldName", "oldEmail", null);

        // act
        userMapper.updateUserFromRequest(targetEntity, request);

        // assert
        assertNotNull(targetEntity);
        assertThat(targetEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    @Test
    void updateUserFromRequestPartial() {
        // arrange
        String fullName = "name";
        UUID userId = UUID.randomUUID();

        UpdateUserRequestDto request = new UpdateUserRequestDto(fullName, null);

        // setting up the entity we are to update
        UserEntity targetEntity = new UserEntity(userId, "oldName", "oldEmail", null);
        // expected result
        UserEntity expectedEntity = new UserEntity(userId, fullName, targetEntity.getEmail(), null);

        // act
        userMapper.updateUserFromRequest(targetEntity, request);

        // assert
        assertNotNull(targetEntity);
        assertThat(targetEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }
}