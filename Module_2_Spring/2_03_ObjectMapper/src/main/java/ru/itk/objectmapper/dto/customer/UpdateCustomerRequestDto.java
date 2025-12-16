package ru.itk.objectmapper.dto.customer;

public record UpdateCustomerRequestDto(
        String firstName,

        String lastName,

        String email,

        String contactNumber
) {
}
