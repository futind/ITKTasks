package ru.itk.objectmapper.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.itk.objectmapper.dto.order.OrderDto;
import ru.itk.objectmapper.model.OrderItemEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {ProductMapper.class})
public interface OrderItemMapper {

    OrderDto.OrderItemDto toDto(OrderItemEntity orderItemEntity);
}
