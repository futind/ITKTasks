package ru.itk.jdbcbooklibrary.service;

import org.springframework.stereotype.Service;
import ru.itk.jdbcbooklibrary.exception.BookNotFoundException;
import ru.itk.jdbcbooklibrary.model.BookDto;
import ru.itk.jdbcbooklibrary.model.CreateBookDto;
import ru.itk.jdbcbooklibrary.model.UpdateBookDto;
import ru.itk.jdbcbooklibrary.repository.BookRepository;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookDto getBookById(UUID id) {
        return bookRepository.findByID(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public BookDto createBook(CreateBookDto createBookDto) {
        return bookRepository.save(createBookDto);
    }

    public BookDto updateBook(UUID id, UpdateBookDto updateBookDto) {
        return bookRepository.updateByID(id, updateBookDto).orElseThrow(() -> new BookNotFoundException(id));
    }

    public void deleteBook(UUID id) {
        if (!bookRepository.deleteByID(id)) {
            throw new BookNotFoundException(id);
        }
    }
}
