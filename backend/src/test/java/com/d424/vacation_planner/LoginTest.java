package com.d424.vacation_planner;

import com.d424.vacation_planner.controller.UserController;
import com.d424.vacation_planner.dto.LoginRequestDto;
import com.d424.vacation_planner.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginTest {

    @Autowired
    private UserController userController;

    @Test
    void testLoginSuccess() {
        // Registered test user:
        String email = "jane.doe@example.com";
        String password = "jane.doe.password";

        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(email);
        loginRequestDto.setPassword(password);

        ResponseEntity<?> response = userController.login(loginRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());
    }
    @Test
    void testLoginUnauthorized() {
        // Registered test user with wrong password:
        String email = "jane.doe@example.com";
        String password = "wrong.password";

        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(email);
        loginRequestDto.setPassword(password);

        ResponseEntity<?> response = userController.login(loginRequestDto);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        assertEquals("Invalid credentials.", response.getBody());
    }
}
