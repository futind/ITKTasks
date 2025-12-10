package ru.itk.pageablebooklibrary.publisher.mapper;

import org.mapstruct.*;
import ru.itk.pageablebooklibrary.publisher.dto.CreatePublisherRequestDto;
import ru.itk.pageablebooklibrary.publisher.dto.PublisherDto;
import ru.itk.pageablebooklibrary.publisher.dto.UpdatePublisherRequestDto;
import ru.itk.pageablebooklibrary.publisher.model.PublisherEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PublisherMapper {

    PublisherDto toDto(PublisherEntity entity);

    @Mapping(target = "id", ignore = true)
    PublisherEntity createFromRequest(CreatePublisherRequestDto request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(@MappingTarget PublisherEntity publisherEntity,
                                 UpdatePublisherRequestDto request);
}
