package ru.itk.pageablebooklibrary.book.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itk.pageablebooklibrary.book.api.BookAPI;
import ru.itk.pageablebooklibrary.book.dto.BookDto;
import ru.itk.pageablebooklibrary.book.dto.CreateBookRequestDto;
import ru.itk.pageablebooklibrary.book.dto.UpdateBookRequestDto;
import ru.itk.pageablebooklibrary.book.service.BookService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/books")
public class BookController implements BookAPI {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * GET /api/v1/books?pageNumber=&pageSize=
     * <p>
     * A method to list a certain amount of books (with pagination)
     *
     * @param pageNumber - a page of authors we want to see
     * @param pageSize   - a size of a page we want to see
     * @param sortField - a field by which a page is going to be sorted
     * @param sortDirection - a direction in which a page is going to be sorted
     * @return a page of authors in form of {@link Page <BookDto>}
     */
    @Override
    @GetMapping
    public ResponseEntity<Page<BookDto>> getBooks(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                                  @RequestParam(name = "pageSize", defaultValue = "20") int pageSize,
                                                  @RequestParam(name = "sortField", defaultValue = "title") String sortField,
                                                  @RequestParam(name = "sortDirection", defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(bookService.getBooks(pageNumber, pageSize, sortField, sortDirection));
    }

    /**
     * GET /api/v1/books/{bookId}
     * <p>
     * A method to get information about a particular book by ID
     *
     * @param bookId - unique identifier of a book in the system (not ISBN)
     * @return information about the book
     */
    @Override
    @GetMapping(value = "/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable UUID bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    /**
     * GET /api/v1/books/find?isbn=
     * <p>
     * A method to get information about a certain book by ISBN
     *
     * @param isbn - unique identifier of a book ISBN
     * @return information about the book
     */
    @Override
    @GetMapping(value = "/find")
    public ResponseEntity<BookDto> getBookByIsbn(@RequestParam(name = "isbn") String isbn) {
        return ResponseEntity.ok(bookService.getBookByISBN(isbn));
    }

    /**
     * POST /api/v1/books
     * <p>
     * A method to create a new book
     *
     * @param createBookRequestDto - information needed to create a book
     * @return information about the created book
     */
    @Override
    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid CreateBookRequestDto createBookRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.createBook(createBookRequestDto));
    }

    /**
     * PATCH /api/v1/books/{bookId}
     * <p>
     * A method to update information of an existing book
     *
     * @param bookId               - unique identifier of a book in the system
     * @param updateBookRequestDto - information needed to update a book
     * @return information about the updated book
     */
    @Override
    @PatchMapping(value = "/{bookId}")
    public ResponseEntity<BookDto> updateBook(@PathVariable UUID bookId,
                                              @RequestBody @Valid UpdateBookRequestDto updateBookRequestDto) {
        return ResponseEntity.ok(bookService.updateBook(bookId, updateBookRequestDto));
    }

    /**
     * DELETE /api/v1/books/{bookId}
     * <p>
     * A method to delete a particular book from the system
     *
     * @param bookId - unique identifier of a book
     */
    @Override
    @DeleteMapping(value = "/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
}
