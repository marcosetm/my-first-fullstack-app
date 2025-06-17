package com.d424.vacation_planner.controller;

import com.d424.vacation_planner.dto.UserDto;
import com.d424.vacation_planner.entity.User;
import com.d424.vacation_planner.mapper.UserMapper;
import com.d424.vacation_planner.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST
    @PostMapping
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        UserDto responseDto = UserMapper.toDto(registeredUser);
        return ResponseEntity.ok(responseDto);
    }

    // GET
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.findAllUsers().stream()
                .map(UserMapper::toDto)
                .toList();
        return ResponseEntity.ok(allUsers);
    }
}
