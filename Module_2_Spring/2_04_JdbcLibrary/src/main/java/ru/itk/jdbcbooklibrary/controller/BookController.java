package ru.itk.jdbcbooklibrary.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itk.jdbcbooklibrary.model.BookDto;
import ru.itk.jdbcbooklibrary.model.CreateBookDto;
import ru.itk.jdbcbooklibrary.model.UpdateBookDto;
import ru.itk.jdbcbooklibrary.service.BookService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks() {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @GetMapping(value = "/{bookId}")
    public ResponseEntity<BookDto> findBookById(@PathVariable UUID bookId) {
        return ResponseEntity.ok().body(bookService.getBookById(bookId));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid CreateBookDto createBookDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.createBook(createBookDto));
    }

    @PatchMapping(value = "/{bookId}")
    public ResponseEntity<BookDto> updateBook(@PathVariable UUID bookId,
                              @RequestBody @Valid UpdateBookDto updateBookDto) {
        return ResponseEntity.ok().body(bookService.updateBook(bookId, updateBookDto));
    }

    @DeleteMapping(value = "/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }


}
