package ru.itk.objectmapper.dto.customer;

import java.util.UUID;

public record CustomerDto(
        UUID id,

        String firstName,

        String lastName,

        String email,

        String contactNumber
) {
}
