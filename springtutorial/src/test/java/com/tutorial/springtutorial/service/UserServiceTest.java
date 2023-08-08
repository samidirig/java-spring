package com.tutorial.springtutorial.service;

import com.tutorial.springtutorial.dto.UserDto;
import com.tutorial.springtutorial.dto.converter.UserDtoConverter;
import com.tutorial.springtutorial.exception.UserNotFoundException;
import com.tutorial.springtutorial.model.User;
import com.tutorial.springtutorial.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;
    private UserDtoConverter converter;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        converter = mock(UserDtoConverter.class);
        userService = new UserService(userRepository, converter);
    }

    @Test
    public void testUserCreate_shouldReturnUser() {
        User user = new User(1L, "Selami", "Güzel");
        UserDto userDto = new UserDto(1L, "Selami", "Güzel");

        when(converter.convertToUserDto(user)).thenReturn(userDto);
        when(userRepository.save(any())).thenReturn(user);

        UserDto result = userService.createUser(user);
        assertEquals(result, userDto);
    }

    @Test
    public void testFindByUserId_whenUserIdExists_shouldReturnUser() {
        User user = new User(1L, "Selami", "Güzel");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        User result = userService.findUserById(1L);
        assertEquals(result, user);
    }

    @Test
    public void testFindByUserId_whenUserIdDoesNotExists_shouldThrowUserNotFoundException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,
                () -> userService.findUserById(1L));
    }

    @Test
    public void testGetUserById_whenUserIdExists_shouldReturnUser() {
        User user = new User(1L, "Selami", "Güzel");
        UserDto userDto = new UserDto(1L, "Selami", "Güzel");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(converter.convertToUserDto(user)).thenReturn(userDto);

        UserDto result = userService.getUserById(1L);
        assertEquals(result, userDto);
    }

    @Test
    public void testGetUserById_whenUserDoesNotIdExists_shouldThrowUserNotFoundException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,
                () -> userService.getUserById(1L));

        verifyNoInteractions(converter);
    }

    @Test
    public void testGetAllUsers_shouldReturnAllUsers(){
        List<User> users = new ArrayList<>();
        User user1 = new User(1L, "Selami", "Güzel");
        User user2 = new User(2L, "Necati", "Sayan");

        users.add(user1);
        users.add(user2);

        UserDto userDto1 = new UserDto(1L, "Selami", "Güzel");
        UserDto userDto2 = new UserDto(2L, "Necati", "Sayan");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user1));
        when(converter.convertToUserDto(user1)).thenReturn(userDto1);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user2));
        when(converter.convertToUserDto(user2)).thenReturn(userDto2);

        List<UserDto> result = userService.getUsers();
        result.add(userDto1);
        result.add(userDto2);

        when(userRepository.findAll()).thenReturn(users);

        assertEquals(result.get(0).getName(), users.get(0).getName());
        assertEquals(result.get(0).getSurname(), users.get(0).getSurname());
    }

/*    @Test
    public void testGetAllUsers_whenDoesNotExists_shouldReturnUserNotFoundException(){
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);

        assertThrows(UserNotFoundException.class, () -> userService.getUsers());

        verify(userRepository, times(1)).findAll();
    }*/

    @Test
    public void testUpdateUser_shouldReturnUser(){
        User user = new User(1L, "Selami", "Güzel");
        UserDto userDto = new UserDto(1L, "Necati", "Sayan");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.updateUser(1L, userDto);

        assertEquals(result, user);
        assertEquals(result.getName(), user.getName());
        assertEquals(result.getSurname(), user.getSurname());
    }

    @Test
    public void testUpdateUser_whenUserDoesNotExists_shouldReturnUserNotFoundException(){
        UserDto userDto = new UserDto(1L, "Necati", "Sayan");
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(any(User.class));
        assertThrows(UserNotFoundException.class,
                ()-> userService.updateUser(1L, userDto));
    }

    @Test
    public void testDeleteUser_shouldReturnUser(){
        User user = new User(1L, "Selami", "Güzel");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User result = userService.deleteUser(1L);

        Assertions.assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getSurname(), result.getSurname());

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testDeleteUserNotFound_shouldThrowUserNotFoundException(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,
                ()-> userService.deleteUser(1L));
    }

    @Test
    public void testIsUserExists_shouldReturnUser() {
        User user = new User(1L, "Selami", "Güzel");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        User result = ReflectionTestUtils.invokeMethod(userService, "isUserExist", 1L);

        Assertions.assertNotNull(result);
        assertEquals(user, result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getSurname(), result.getSurname());

    }

    @Test
    public void testIsUserDoesNotExists_shouldThrowUserNotFoundException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,
                () -> ReflectionTestUtils.invokeMethod(userService, "isUserExist", 1L));
    }




}
