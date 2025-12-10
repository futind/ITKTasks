package ru.itk.pageablebooklibrary.book.mapper;

import org.mapstruct.*;
import ru.itk.pageablebooklibrary.author.mapper.AuthorMapper;
import ru.itk.pageablebooklibrary.author.model.AuthorEntity;
import ru.itk.pageablebooklibrary.book.dto.BookDto;
import ru.itk.pageablebooklibrary.book.dto.CreateBookRequestDto;
import ru.itk.pageablebooklibrary.book.dto.UpdateBookRequestDto;
import ru.itk.pageablebooklibrary.book.model.BookEntity;
import ru.itk.pageablebooklibrary.publisher.mapper.PublisherMapper;
import ru.itk.pageablebooklibrary.publisher.model.PublisherEntity;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                AuthorMapper.class,
                PublisherMapper.class
        }
)
public interface BookMapper {

    BookDto toDto(BookEntity bookEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", source = "authorEntity")
    @Mapping(target = "publisher", source = "publisherEntity")
    BookEntity createEntityFromRequest(
            CreateBookRequestDto request,
            AuthorEntity authorEntity,
            PublisherEntity publisherEntity
    );

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", source = "authorEntity")
    @Mapping(target = "publisher", source = "publisherEntity")
    void updateEntityFromRequest(
            @MappingTarget BookEntity bookEntity,
            UpdateBookRequestDto request,
            AuthorEntity authorEntity,
            PublisherEntity publisherEntity
    );
}
