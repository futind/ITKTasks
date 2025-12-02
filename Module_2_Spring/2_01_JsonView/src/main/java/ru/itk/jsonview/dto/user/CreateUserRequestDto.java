package ru.itk.jsonview.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * A DTO needed to create a new user. All the fields are required.
 * @param fullName
 * @param email
 */
public record CreateUserRequestDto(
        @NotBlank String fullName,
        @NotBlank @Email String email
) {}
