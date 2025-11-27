package ru.itk.jsonview.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itk.jsonview.dto.user.CreateUserRequestDto;
import ru.itk.jsonview.dto.user.UpdateUserRequestDto;
import ru.itk.jsonview.dto.user.UserDto;
import ru.itk.jsonview.dto.user.UserView;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class JsonViewController {

    // list all users (without details)
    @JsonView(UserView.Summary.class)
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getUsersSummary() {
        return null;
    }

    // list all users (with details)
    @JsonView(UserView.Detailed.class)
    @GetMapping(value = "/users/detailed")
    public ResponseEntity<List<UserDto>> getUsersDetailed() {
        return null;
    }

    // show details about one particular user
    @JsonView(UserView.Detailed.class)
    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID userId) {
        return null;
    }

    // create a new user
    @JsonView(UserView.Summary.class)
    @PostMapping(value = "/users")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid CreateUserRequestDto request) {
        return null;
    }

    // update particular user
    @JsonView(UserView.Detailed.class)
    @PatchMapping(value = "/users/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID userId,
                                              @RequestBody @Valid UpdateUserRequestDto request) {
        return null;
    }

    // delete particular user
    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        return null;
    }
}
