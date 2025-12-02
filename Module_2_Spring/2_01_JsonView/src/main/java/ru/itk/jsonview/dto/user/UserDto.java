package ru.itk.jsonview.dto.user;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.itk.jsonview.dto.OrderDto;

import java.util.List;
import java.util.UUID;

/**
 * A DTO describing the user.
 * @param id - unique identifier
 * @param fullName - full name of the user
 * @param email - email of the user
 * @param orders - person's orders {@link OrderDto}
 */
public record UserDto(
        @JsonView(UserView.Summary.class)
        @NotNull
        UUID id,

        @JsonView(UserView.Summary.class)
        @NotBlank
        String fullName,

        @JsonView(UserView.Summary.class)
        @NotBlank
        @Email
        String email,

        @JsonView(UserView.Detailed.class)
        List<OrderDto> orders
) {}
