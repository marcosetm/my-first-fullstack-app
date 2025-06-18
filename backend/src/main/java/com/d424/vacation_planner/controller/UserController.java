package com.d424.vacation_planner.controller;

import com.d424.vacation_planner.dto.LoginRequestDto;
import com.d424.vacation_planner.dto.UserDto;
import com.d424.vacation_planner.entity.User;
import com.d424.vacation_planner.mapper.UserMapper;
import com.d424.vacation_planner.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // POST /api/users
    // Create/register a user
    @PostMapping
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        UserDto responseDto = UserMapper.toDto(registeredUser);
        return ResponseEntity.ok(responseDto);
    }

    // POST /api/users/login
    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        return userService.getUserByEmail(request.getEmail())
                .map(user -> {
                    if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                        return ResponseEntity.ok(UserMapper.toDto(user));
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials."));
    }

    // GET /api/users
    // Get all users - admin view or troubleshooting
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers().stream()
                .map(UserMapper::toDto)
                .toList();
        return ResponseEntity.ok(allUsers);
    }
    // GET /api/users/{id}
    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserDto responseDto = UserMapper.toDto(user);
        return ResponseEntity.ok(responseDto);
    }
}
