package com.subscribe.account.service;

import com.subscribe.account.dto.UserDto;
import com.subscribe.account.dto.converter.UserDtoConverter;
import com.subscribe.account.exception.UserNotFoundException;
import com.subscribe.account.model.User;
import com.subscribe.account.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public UserDto createUser(User user) {
        return userDtoConverter.convertToUserDto(userRepository.save(user));
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDtoConverter::convertToUserDto)
                .collect(Collectors.toList());
    }

    protected User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new UserNotFoundException("User could not find by id: " + userId));
    }

    public UserDto getUserById(Long userId) {
        return userDtoConverter.convertToUserDto(findUserById(userId));
    }

    public User updateUser(Long userId, UserDto newUser) {
        User updateUser = isUserExist(userId);
        updateUser.setUserName(newUser.getUserName());
        updateUser.setName(newUser.getName());
        updateUser.setSurname(newUser.getSurname());
        return userRepository.save(updateUser);
    }

    private User isUserExist(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User could not find by id :" + userId);
        }
        return user;
    }

    public User deleteUser(Long userId) {
        User user = isUserExist(userId);
        userRepository.delete(user);
        return user;
    }
}
