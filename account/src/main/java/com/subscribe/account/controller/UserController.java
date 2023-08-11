package com.subscribe.account.controller;

import com.subscribe.account.dto.UserDto;
import com.subscribe.account.model.User;
import com.subscribe.account.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto user) {
        return ResponseEntity.ok(userService.updateUser(userId, user));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}
