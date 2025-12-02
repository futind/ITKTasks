package ru.itk.jsonview.dto.user;

import jakarta.validation.constraints.Email;

/**
 * DTO which is needed in order to update an existing user. All the fields are optional.
 * @param fullName
 * @param email
 */
public record UpdateUserRequestDto(
        String fullName,
        @Email String email
) {}
