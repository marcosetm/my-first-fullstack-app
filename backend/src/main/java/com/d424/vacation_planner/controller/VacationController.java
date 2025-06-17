package com.d424.vacation_planner.controller;

import com.d424.vacation_planner.dto.VacationDto;
import com.d424.vacation_planner.entity.User;
import com.d424.vacation_planner.entity.Vacation;
import com.d424.vacation_planner.mapper.VacationMapper;
import com.d424.vacation_planner.service.UserService;
import com.d424.vacation_planner.service.VacationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vacations")
public class VacationController {

    private final VacationService vacationService;
    private final UserService userService;

    @Autowired
    public VacationController(VacationService vacationService, UserService userService) {
        this.vacationService = vacationService;
        this.userService = userService;
    }

    // POST
    @PostMapping
    public ResponseEntity<VacationDto> createVacation(
            @RequestParam Long userId,
            @Valid @RequestBody Vacation vacationRequest) {
        User user = userService.findUserById(userId);
        Vacation createdVacation = vacationService.createVacation(user, vacationRequest);
        VacationDto dto = VacationMapper.toDto(createdVacation);
        return ResponseEntity.ok(dto);
    }

    // GET /api/vacations?userId=1 for example
    @GetMapping
    public ResponseEntity<List<VacationDto>> getVacationsByUser(@RequestParam Long userId) {
        List<VacationDto> vacations = vacationService.getVacationsByUser(userId)
                .stream()
                .map(VacationMapper::toDto)
                .toList();
        return ResponseEntity.ok(vacations);
    }
}
