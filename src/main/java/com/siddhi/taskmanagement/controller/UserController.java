package com.siddhi.taskmanagement.controller;

import com.siddhi.taskmanagement.dto.UserDto;
import com.siddhi.taskmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // CREATE - Register new user
    @PostMapping("/api/users")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // READ - Get user by ID
    @GetMapping("/api/users/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // READ - Get user by email
    @GetMapping("/api/users/email/{email}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        UserDto user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    // READ - Get all users
    @GetMapping("/api/users")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // UPDATE - Update user details
    @PutMapping("/api/users/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserDto userDto) {

        UserDto updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE - Delete user
    @DeleteMapping("/api/users/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
