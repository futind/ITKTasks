package ru.itk.jsonview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itk.jsonview.dto.OrderDto;
import ru.itk.jsonview.model.order.OrderEntity;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toDto(OrderEntity orderEntity);

    @Mapping(target = "id", ignore = true)
    OrderEntity toEntity(OrderDto orderDto);
}
