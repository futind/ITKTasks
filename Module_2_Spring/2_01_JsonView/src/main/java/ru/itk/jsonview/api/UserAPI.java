package ru.itk.jsonview.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import ru.itk.jsonview.dto.user.CreateUserRequestDto;
import ru.itk.jsonview.dto.user.UpdateUserRequestDto;
import ru.itk.jsonview.dto.user.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserAPI {

    /**
     * GET /api/v1/users
     * A method to get information about all the users without details
     * @return a list of users {@link UserDto} without details
     */
    @Operation(
            summary = "Get information about all users (w/o details)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json"
                            // ,array = @ArraySchema(schema = @Schema(implementation = UserDto.class)) - but not all the fields are shown bruh
                    )
            })
    })
    ResponseEntity<List<UserDto>> getUsersSummary();

    /**
     * GET /api/v1/users/detailed
     * A method to get information about all the users with additional details
     * @return a list of users {@link UserDto} with details
     */
    @Operation(
            summary = "Get information about all users (with details)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                    )
            })
    })
    ResponseEntity<List<UserDto>> getUsersDetailed();

    /**
     * GET /api/v1/users/{userId}
     * A method which returns full information (with details) about a particular user with provided userId
     * @param userId - {@link UUID} - unique identification of a user
     * @return full information about the user - {@link UserDto}
     */
    @Operation(
            summary = "Get information about a certain user (with details)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "User not found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            })
    })
    ResponseEntity<UserDto> getUserById(UUID userId);

    /**
     * POST /api/v1/users
     * A method which creates a user using the provided information
     * @param request - information needed to create a new user - {@link CreateUserRequestDto}
     * @return information about created user - {@link UserDto}
     */
    @Operation(
            summary = "Create a new user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json"
                            //, schema = @Schema(implementation = UserDto.class) - again not in full, nothing to do here other that doing in by hands with @SchemaProperty
                    )
            })
    })
    ResponseEntity<UserDto> createUser(CreateUserRequestDto request);

    /**
     * PATCH /api/v1/users/{userId}
     * A method which updates an existing user with provided information
     * @param userId - {@link UUID} - unique identification of a user
     * @param request - information needed to update user's information
     * @return full information about the user - {@link UserDto}
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "User not found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            })
    })
    @Operation(
            summary = "Updated an existing user"
    )
    ResponseEntity<UserDto> updateUser(UUID userId, UpdateUserRequestDto request);

    /**
     * DELETE /api/v1/users/{userId}
     * A method which deletes an existing user
     * @param userId - {@link UUID} - unique identification of a user
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "User not found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            })
    })
    @Operation(
            summary = "Delete an existing user"
    )
    ResponseEntity<Void> deleteUser(UUID userId);
}
