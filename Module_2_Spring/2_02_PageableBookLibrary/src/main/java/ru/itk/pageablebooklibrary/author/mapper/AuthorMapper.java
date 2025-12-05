package ru.itk.pageablebooklibrary.author.mapper;

import org.mapstruct.*;
import ru.itk.pageablebooklibrary.author.dto.AuthorDto;
import ru.itk.pageablebooklibrary.author.dto.CreateAuthorRequestDto;
import ru.itk.pageablebooklibrary.author.dto.UpdateAuthorRequestDto;
import ru.itk.pageablebooklibrary.author.model.AuthorEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AuthorMapper {

    AuthorDto toDto(AuthorEntity authorEntity);

    @Mapping(target = "id", ignore = true)
    AuthorEntity createFromRequest(CreateAuthorRequestDto request);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromRequest(@MappingTarget AuthorEntity authorEntity,
                                   UpdateAuthorRequestDto request);
}
