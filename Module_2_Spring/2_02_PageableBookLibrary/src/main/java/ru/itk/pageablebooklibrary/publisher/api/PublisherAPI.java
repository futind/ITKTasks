package ru.itk.pageablebooklibrary.publisher.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import ru.itk.pageablebooklibrary.publisher.dto.CreatePublisherRequestDto;
import ru.itk.pageablebooklibrary.publisher.dto.PublisherDto;
import ru.itk.pageablebooklibrary.publisher.dto.UpdatePublisherRequestDto;

import java.util.UUID;

public interface PublisherAPI {

    /**
     * GET /api/v1/publisher?pageNumber=&pageSize=
     * <p>
     * A method to list a certain amount of publisher (with pagination)
     * @param pageNumber - a page of authors we want to see
     * @param pageSize - a size of a page we want to see
     * @param sortField - field by with we sort
     * @param sortDirection - direction of the sort
     * @return a page of authors in form of {@link Page<PublisherDto>}
     */
    ResponseEntity<Page<PublisherDto>> getPublishers(int pageNumber, int pageSize, String sortField, String sortDirection);

    /**
     * GET /api/v1/publisher/{publisherId}
     * <p>
     * A method to show the information about the publisher
     * @param publisherId - unique identifier of a publisher
     * @return information about the publisher in form of {@link PublisherDto}
     */
    @Operation(
            summary = "Get information about the publisher"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PublisherDto.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "Publisher with given id was not found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            })
    })
    ResponseEntity<PublisherDto> getPublisherById(UUID publisherId);

    /**
     * POST /api/v1/publisher
     * <p>
     * A method to create a new publisher
     * @param createPublisherRequestDto - information needed to create a new publisher
     * @return information about the created publisher in form of {@link PublisherDto}
     */
    @Operation(
            summary = "Create a new publisher"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PublisherDto.class)
                    )
            })
    })
    ResponseEntity<PublisherDto> createPublisher(CreatePublisherRequestDto createPublisherRequestDto);

    /**
     * PATCH /api/v1/publisher/{publisherId}
     * <p>
     * A method to update the information about an existing publisher
     * @param publisherId - unique identifier of a publisher
     * @param updatePublisherRequestDto - information needed to update the information about the publisher
     * @return information about the updated publisher in form of {@link PublisherDto}
     */
    @Operation(
            summary = "Update information about the publisher"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PublisherDto.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "Publisher with provided id was not found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            })
    })
    ResponseEntity<PublisherDto> updatePublisher(UUID publisherId, UpdatePublisherRequestDto updatePublisherRequestDto);

    /**
     * DELETE /api/v1/publisher/{publisherId}?force=
     * <p>
     * A method to delete an existing publisher
     * @param publisherId - unique identifier of a publisher
     */
    @Operation(
            summary = "Delete an existing publisher"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "Can't delete publisher - dependent entities found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "Publisher with provided id was not found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            })
    })
    ResponseEntity<Void> deletePublisher(UUID publisherId);
}
