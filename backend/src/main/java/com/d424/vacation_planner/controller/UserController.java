package com.d424.vacation_planner.controller;

import com.d424.vacation_planner.dto.UserDto;
import com.d424.vacation_planner.entity.User;
import com.d424.vacation_planner.entity.Vacation;
import com.d424.vacation_planner.mapper.UserMapper;
import com.d424.vacation_planner.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST /api/users
    // Create/register a user
    @PostMapping
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        UserDto responseDto = UserMapper.toDto(registeredUser);
        return ResponseEntity.ok(responseDto);
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
