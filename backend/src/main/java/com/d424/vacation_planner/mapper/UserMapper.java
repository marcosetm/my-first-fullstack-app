package com.d424.vacation_planner.mapper;

import com.d424.vacation_planner.dto.UserDto;
import com.d424.vacation_planner.entity.User;

public class UserMapper {

    // Omitting dob and password on purpose

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
    public static User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        return user;
    }
}
