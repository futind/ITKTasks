package ru.itk.jsonview.dto.user;

import jakarta.validation.constraints.Email;

public record UpdateUserRequestDto(String fullName, @Email String email) {}
