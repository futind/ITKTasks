package ru.itk.pageablebooklibrary.author.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import ru.itk.pageablebooklibrary.author.dto.AuthorDto;
import ru.itk.pageablebooklibrary.author.dto.CreateAuthorRequestDto;
import ru.itk.pageablebooklibrary.author.dto.UpdateAuthorRequestDto;

import java.util.UUID;

@Tag(name = "Author Management", description = "Operations related to authors")
public interface AuthorAPI {

    /**
     * GET /api/v1/authors?pageNumber=&pageSize=&sortField=&sortDirection=
     * <p>
     * A method to list a certain amount of authors (with pagination)
     * @param pageNumber - a number of a page of authors to show
     * @param pageSize - a size of a page to show
     * @param sortField - a field by which a page is going to be sorted
     * @param sortDirection - a direction in which a page is going to be sorted
     * @return a page of authors in form of {@link Page<AuthorDto>}
     */
    ResponseEntity<Page<AuthorDto>> getAuthors(int pageNumber, int pageSize, String sortField, String sortDirection);

    /**
     * GET /api/v1/authors/{authorId}
     * <p>
     * Method to get the info about a particular author by id
     * @param authorId - unique personal identifier of an author
     * @return basic information about the author in form of {@link AuthorDto}
     */
    @Operation(
            summary = "Get information about a particular author"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthorDto.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "Author was not found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            })
    })
    ResponseEntity<AuthorDto> getAuthorById(UUID authorId);

    /**
     * PATCH /api/v1/authors/{authorId}
     * <p>
     * Method to update the information of a particular author
     * @param authorId - unique personal identifier of an author
     * @param updateAuthorRequestDto - data needed to update the author's info
     * @return updated information about the author in form of {@link AuthorDto}
     */
    @Operation(
            summary = "Update an existing author"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthorDto.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "Author was not found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            })
    })
    ResponseEntity<AuthorDto> updateAuthor(UUID authorId, UpdateAuthorRequestDto updateAuthorRequestDto);

    /**
     * POST /api/v1/authors
     * <p>
     * Method to create a new user
     * @param createAuthorRequestDto - info needed to create a new author
     * @return information about the created author
     */
    @Operation(
            summary = "Create a new author"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthorDto.class)
                    )
            })
    })
    ResponseEntity<AuthorDto> createAuthor(CreateAuthorRequestDto createAuthorRequestDto);

    /**
     * DELETE /api/v1/authors/{authorId}
     * <p>
     * Method to delete a particular user
     * @param authorId - unique personal identifier of an author
     */
    @Operation(
            summary = "Delete an existing user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "Can't delete that author - dependent entities found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "Author was not found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            })
    })
    ResponseEntity<Void> deleteAuthor(UUID authorId);
}
