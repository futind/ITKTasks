package ru.itk.jsonview.mapper;

import org.mapstruct.*;
import ru.itk.jsonview.dto.user.CreateUserRequestDto;
import ru.itk.jsonview.dto.user.UpdateUserRequestDto;
import ru.itk.jsonview.dto.user.UserDto;
import ru.itk.jsonview.model.user.UserEntity;


@Mapper(componentModel = "spring", uses = {OrderMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserDto toDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    UserEntity createFromRequest(CreateUserRequestDto createUserRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromRequest(@MappingTarget UserEntity userEntity,
                               UpdateUserRequestDto updateUserRequestDto);
}
