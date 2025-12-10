package ru.itk.pageablebooklibrary.book.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import ru.itk.pageablebooklibrary.book.dto.BookDto;
import ru.itk.pageablebooklibrary.book.dto.CreateBookRequestDto;
import ru.itk.pageablebooklibrary.book.dto.UpdateBookRequestDto;

import java.util.UUID;

public interface BookAPI {

    /**
     * GET /api/v1/books?pageNumber=&pageSize=&sortField=&sortDirection=
     * <p>
     * A method to list a certain amount of books (with pagination)
     * @param pageNumber - a page of authors we want to see
     * @param pageSize   - a size of a page we want to see
     * @param sortField - a field by which a page is going to be sorted
     * @param sortDirection - a direction in which a page is going to be sorted
     * @return a page of authors in form of {@link Page<BookDto>}
     */
    ResponseEntity<Page<BookDto>> getBooks(int pageNumber, int pageSize, String sortField, String sortDirection);

    /**
     * GET /api/v1/books/{bookId}
     * <p>
     * A method to get information about a particular book by ID
     * @param bookId - unique identifier of a book in the system (not ISBN)
     * @return information about the book
     */
    @Operation(
            summary = "Get information about a particular book"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BookDto.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "Book with provided ID was not found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            })
    })
    ResponseEntity<BookDto> getBookById(UUID bookId);

    /**
     * GET /api/v1/books?isbn=
     * <p>
     * A method to get information about a certain book by ISBN
     * @param isbn - unique identifier of a book ISBN
     * @return information about the book
     */
    @Operation(
            summary = "Get information about a particular book"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BookDto.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "Book with provided ISBN was not found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            })
    })
    ResponseEntity<BookDto> getBookByIsbn(String isbn);

    /**
     * POST /api/v1/books
     * <p>
     * A method to create a new book
     * @param createBookRequestDto - information needed to create a book
     * @return information about the created book
     */
    @Operation(
            summary = "Get information about a particular book"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BookDto.class)
                    )
            }),
            @ApiResponse(responseCode = "403", description = "Book with provided ISBN already exists", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            })
    })
    ResponseEntity<BookDto> createBook(CreateBookRequestDto createBookRequestDto);

    /**
     * PATCH /api/v1/books/{bookId}
     * <p>
     * A method to update information of an existing book
     * @param bookId - unique identifier of a book in the system
     * @param updateBookRequestDto - information needed to update a book
     * @return information about the updated book
     */
    @Operation(
            summary = "Update information about an existing book"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BookDto.class)
                    )
            }),
            @ApiResponse(responseCode = "403", description = "Updated ISBN already exists", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "Book with provided ID was not found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            })
    })
    ResponseEntity<BookDto> updateBook(UUID bookId, UpdateBookRequestDto updateBookRequestDto);

    /**
     * DELETE /api/v1/books/{bookId}
     * <p>
     * A method to delete a particular book from the system
     * @param bookId - unique identifier of a book
     */
    @Operation(
            summary = "Delete an existing book"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Book with provided ID was not found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            })
    })
    ResponseEntity<Void> deleteBook(UUID bookId);
}
