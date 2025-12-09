package ru.itk.jdbcbooklibrary.repository;

import ru.itk.jdbcbooklibrary.model.BookDto;
import ru.itk.jdbcbooklibrary.model.CreateBookDto;
import ru.itk.jdbcbooklibrary.model.UpdateBookDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository {

    List<BookDto> findAll();

    Optional<BookDto> findByID(UUID id);

    Boolean deleteByID(UUID id);

    BookDto save(CreateBookDto createBookDto);

    Optional<BookDto> updateByID(UUID id, UpdateBookDto updateBookDto);
}
