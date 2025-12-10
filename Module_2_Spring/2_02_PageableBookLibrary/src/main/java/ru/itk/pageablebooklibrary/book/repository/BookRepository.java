package ru.itk.pageablebooklibrary.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itk.pageablebooklibrary.book.dto.BookDto;
import ru.itk.pageablebooklibrary.book.model.BookEntity;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {
    Optional<BookEntity> findByISBN(String isbn);

    boolean existsByISBN(String isbn);
}
