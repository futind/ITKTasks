package ru.itk.objectmapper.mapper;

import org.mapstruct.*;
import ru.itk.objectmapper.dto.order.OrderDto;
import ru.itk.objectmapper.dto.order.UpdateOrderRequestDto;
import ru.itk.objectmapper.model.OrderEntity;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                OrderItemMapper.class,
                CustomerMapper.class
        }
)
public interface OrderMapper {

    OrderDto toDto(OrderEntity orderEntity);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrder(@MappingTarget OrderEntity orderEntity,
                     UpdateOrderRequestDto updateRequest);
}
