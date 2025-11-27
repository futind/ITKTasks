package ru.itk.jsonview.dto.user;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import ru.itk.jsonview.dto.OrderDto;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class UserDto {

    @JsonView(UserView.Summary.class)
    @NotNull
    private final UUID id;

    @JsonView(UserView.Summary.class)
    @NotBlank
    private final String fullName;

    @JsonView(UserView.Summary.class)
    @Email
    private final String email;

    @JsonView(UserView.Detailed.class)
    private final List<OrderDto> orders;
}
