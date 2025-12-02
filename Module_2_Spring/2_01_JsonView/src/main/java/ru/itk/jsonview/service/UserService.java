package ru.itk.jsonview.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.itk.jsonview.dto.user.CreateUserRequestDto;
import ru.itk.jsonview.dto.user.UpdateUserRequestDto;
import ru.itk.jsonview.dto.user.UserDto;
import ru.itk.jsonview.exception.UserNotFoundException;
import ru.itk.jsonview.mapper.UserMapper;
import ru.itk.jsonview.model.user.UserEntity;
import ru.itk.jsonview.repository.UserRepository;

import java.util.List;
import java.util.UUID;

/**
 * A service to manage user information
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * A method to get all the user's information
     * @return list with all the user's information {@link UserDto}
     */
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Get information about a particular user
     * @param userId - unique identifier
     * @return information about the user {@link UserDto}
     */
    public UserDto getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto).orElseThrow(() -> new UserNotFoundException(userId));
    }

    /**
     * Create a new user
     * @param request - information needed to create a user {@link CreateUserRequestDto}
     * @return information about the user {@link UserDto}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserDto createUser(CreateUserRequestDto request) {
        UserEntity userEntity = userMapper.createFromRequest(request);
        userEntity = userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    /**
     * Update an existing user
     * @param userId - unique identifier
     * @param request - information needed to update the record {@link UpdateUserRequestDto}
     * @return information about the user {@link UserDto}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserDto updateUser(UUID userId, UpdateUserRequestDto request) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        userMapper.updateUserFromRequest(userEntity, request);
        return userMapper.toDto(userEntity);
    }

    /**
     * Delete an existing user
     * @param userId - unique identifier
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
