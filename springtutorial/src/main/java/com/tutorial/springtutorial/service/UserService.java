package com.tutorial.springtutorial.service;

import com.tutorial.springtutorial.dto.UserDto;
import com.tutorial.springtutorial.dto.converter.UserDtoConverter;
import com.tutorial.springtutorial.exception.UserNotFoundException;
import com.tutorial.springtutorial.model.User;
import com.tutorial.springtutorial.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        user.setCreatedBy("Admin");
        user.setCreatedDate(new Date());
        return userDtoConverter.convertToUserDto(userRepository.save(user));
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDtoConverter::convertToUserDto)
                .collect(Collectors.toList());
    }

    protected User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("User could not find by id: " + id));

    }

    public UserDto getUserById(Long id) {
        return userDtoConverter.convertToUserDto(findUserById(id));
    }

    public User updateUser(Long id, UserDto user) {
        User updateUser = isUserExist(id);
        updateUser.setName(user.getName());
        updateUser.setSurname(user.getSurname());
        updateUser.setUpdatedDate(new Date());
        updateUser.setUpdatedBy("Admin");
        return userRepository.save(updateUser);
    }

    public User deleteUser(Long id) {
        User user = isUserExist(id);
        userRepository.delete(user);
        return user;
    }

    private User isUserExist(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User could not find by id :" + id);
        }
        return user;
    }
}
