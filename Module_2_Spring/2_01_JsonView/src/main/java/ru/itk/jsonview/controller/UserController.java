package ru.itk.jsonview.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itk.jsonview.api.UserAPI;
import ru.itk.jsonview.dto.user.CreateUserRequestDto;
import ru.itk.jsonview.dto.user.UpdateUserRequestDto;
import ru.itk.jsonview.dto.user.UserDto;
import ru.itk.jsonview.dto.user.UserView;
import ru.itk.jsonview.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class UserController implements UserAPI {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * GET /api/v1/users
     * A method to get information about all the users without details
     * @return a list of users {@link UserDto} without details
     */
    @JsonView(UserView.Summary.class)
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getUsersSummary() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    /**
     * GET /api/v1/users/detailed
     * A method to get information about all the users with additional details
     * @return a list of users {@link UserDto} with details
     */
    @JsonView(UserView.Detailed.class)
    @GetMapping(value = "/users/detailed")
    public ResponseEntity<List<UserDto>> getUsersDetailed() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    /**
     * GET /api/v1/users/{userId}
     * A method which returns full information (with details) about a particular user with provided userId
     * @param userId - {@link UUID} - unique identification of a user
     * @return full information about the user - {@link UserDto}
     */
    @JsonView(UserView.Detailed.class)
    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }

    /**
     * POST /api/v1/users
     * A method which creates a user using the provided information
     * @param request - information needed to create a new user - {@link CreateUserRequestDto}
     * @return information about created user - {@link UserDto}
     */
    @JsonView(UserView.Summary.class)
    @PostMapping(value = "/users")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid CreateUserRequestDto request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    /**
     * PATCH /api/v1/users/{userId}
     * A method which updates an existing user with provided information
     * @param userId - {@link UUID} - unique identification of a user
     * @param request - information needed to update user's information
     * @return full information about the user - {@link UserDto}
     */
    @JsonView(UserView.Detailed.class)
    @PatchMapping(value = "/users/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID userId,
                                              @RequestBody @Valid UpdateUserRequestDto request) {
        return ResponseEntity.ok().body(userService.updateUser(userId, request));
    }

    /**
     * DELETE /api/v1/users/{userId}
     * A method which deletes an existing user
     * @param userId - {@link UUID} - unique identification of a user
     */
    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
