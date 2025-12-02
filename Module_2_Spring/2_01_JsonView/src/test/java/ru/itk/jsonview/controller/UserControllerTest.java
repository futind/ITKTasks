package ru.itk.jsonview.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.itk.jsonview.dto.OrderDto;
import ru.itk.jsonview.dto.product.ProductOrderDto;
import ru.itk.jsonview.dto.user.CreateUserRequestDto;
import ru.itk.jsonview.dto.user.UpdateUserRequestDto;
import ru.itk.jsonview.dto.user.UserDto;
import ru.itk.jsonview.exception.UserNotFoundException;
import ru.itk.jsonview.model.order.OrderStatus;
import ru.itk.jsonview.service.UserService;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    private final UUID userId1 = UUID.randomUUID();
    private final UUID userId2 = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        String username1 = "username1";
        String username2 = "username2";
        String email1 = "email1@one.ru";
        String email2 = "email2@one.ru";

        OrderDto orderDto = new OrderDto(
                UUID.randomUUID(),
                userId2,
                OrderStatus.CREATED,
                List.of(
                        new ProductOrderDto(UUID.randomUUID(), BigDecimal.TEN, 2)
                )
        );

        UserDto user1 = new UserDto(userId1, username1, email1, null);
        UserDto user2 = new UserDto(userId2, username2, email2, List.of(orderDto));

        when(userService.getAllUsers()).thenReturn(List.of(user1, user2));

        when(userService.getUserById(any(UUID.class))).thenAnswer(getRequest -> {
            UUID uuid = getRequest.getArgument(0);

            if (!(uuid.equals(userId1) || uuid.equals(userId2))) {
                throw new UserNotFoundException("didnt find user");
            }
            return uuid.equals(userId1) ? user1 : user2;
        });

        when(userService.createUser(any(CreateUserRequestDto.class)))
                .thenAnswer(createRequest -> {
                    CreateUserRequestDto request = createRequest.getArgument(0);
                    return new UserDto(
                            UUID.randomUUID(),
                            request.fullName(),
                            request.email(),
                            null
                            );
                });

        when(userService.updateUser(any(UUID.class), any(UpdateUserRequestDto.class)))
                .thenAnswer(updateRequest -> {
                    UUID uuid = updateRequest.getArgument(0);

                    if (!(uuid.equals(userId1) || uuid.equals(userId2))) {
                        throw new UserNotFoundException("didnt find user");
                    }
                    UserDto oldVersion = uuid.equals(userId1) ? user1 : user2;

                    UpdateUserRequestDto request = updateRequest.getArgument(1);
                    return new UserDto(
                            uuid,
                            request != null ? request.fullName() : oldVersion.fullName(),
                            request != null ? request.email() : oldVersion.email(),
                            oldVersion.orders()
                    );
        });
    }

    @Test
    void listingAllUsersWithoutDetailsDoesNotReturnsOrders() throws Exception {
        String path = "/api/v1/users";
        MvcResult mvcResult = mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].orders").doesNotExist()) // no orders field in json
                .andReturn();

        List<UserDto> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<UserDto>>() {});

        assertNotNull(result);
        assertEquals(2, result.size());
        for(UserDto userDto : result) {
            assertNotNull(userDto);
            assertNull(userDto.orders());
        }
    }

    @Test
    void listingAllUsersWithDetailsReturnsOrders() throws Exception {
        String path = "/api/v1/users/detailed";
        MvcResult mvcResult = mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].orders").exists()) // orders field exists in json (null or whatever)
                .andReturn();

        List<UserDto> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<UserDto>>() {});

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void listUserByIdReturnsUserWithDetails() throws Exception {
        String path = "/api/v1/users/{userId}";
        MvcResult mvcResult = mockMvc.perform(get(path, userId2.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orders").exists()) // returns json with orders field
                .andReturn();

        UserDto result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserDto.class);

        assertNotNull(result);
        assertNotNull(result.orders());
        assertEquals(1, result.orders().size()); // the orders field is serialized correctly
    }

    @Test
    void createUserReturnsCorrectUserWithNoDetails() throws Exception {
        String path = "/api/v1/users";
        CreateUserRequestDto request = new CreateUserRequestDto("fullName", "email@dot.com");

        MvcResult mvcResult = mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orders").doesNotExist())
                .andReturn();

        UserDto result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserDto.class);
        assertNotNull(result);
        assertNotNull(result.id());
        assertEquals(request.fullName(), result.fullName());
        assertEquals(request.email(), result.email());
        assertNull(result.orders());
    }

    @Test
    void updateUserReturnsUserWithDetails() throws Exception {
        String path = "/api/v1/users/{userId}";
        UpdateUserRequestDto request = new UpdateUserRequestDto("newFullName", "new@email.com");

        MvcResult mvcResult = mockMvc.perform(patch(path, userId2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orders").exists())
                .andReturn();

        UserDto result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserDto.class);

        assertNotNull(result);
        assertEquals(userId2, result.id());
        assertEquals(request.fullName(), result.fullName());
        assertEquals(request.email(), result.email());
        assertNotNull(result.orders());
        assertEquals(1, result.orders().size());
    }

    @Test
    void getUserByIdReturns404WhenGivenIncorrectId() throws Exception {
        UUID uuid = UUID.randomUUID();

        String path = "/api/v1/users/{userId}";

        mockMvc.perform(get(path, uuid))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateUserReturns404WhenGivenIncorrectId() throws Exception {
        UUID uuid = UUID.randomUUID();

        String path = "/api/v1/users/{userId}";

        UpdateUserRequestDto request = new UpdateUserRequestDto(null, "newEmail@dot.com");

        mockMvc.perform(patch(path, uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUserReturns404WhenGivenIncorrectId() throws Exception {
        String path = "/api/v1/users/{userId}";
        UUID wrongID = UUID.randomUUID();

        doThrow(new UserNotFoundException("didnt find user")).when(userService).deleteUser(wrongID);

        mockMvc.perform(delete(path, wrongID))
                .andExpect(status().isNotFound());
    }
}