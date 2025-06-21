package com.d424.vacation_planner.controller;

import com.d424.vacation_planner.dto.VacationDto;
import com.d424.vacation_planner.entity.Excursion;
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

    // POST /api/vacations/user/{userId}
    // Create vacation for user ID
    @PostMapping("/user/{userId}")
    public ResponseEntity<VacationDto> createVacation(
            @PathVariable Long userId,
            @RequestBody Vacation vacationBody) {
        User user = userService.getUserById(userId);
        Vacation createdVacation = vacationService.createVacation(vacationBody, user);
        VacationDto dto = VacationMapper.toDto(createdVacation);
        return ResponseEntity.ok(dto);
    }

    // GET /api/vacations/users/{userId}
    // Get vacations for user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<VacationDto>> getVacationsByUser(@PathVariable Long userId) {
        List<VacationDto> vacations = vacationService.getVacationsByUserId(userId)
                .stream()
                .map(VacationMapper::toDto)
                .toList();
        return ResponseEntity.ok(vacations);
    }

    // GET /api/vacations/{vacationId}
    // Get vacation details
    @GetMapping("/{vacationId}")
    public ResponseEntity<VacationDto> getVacationsById(@PathVariable Long vacationId) {
        Vacation vacation = vacationService.getVacationById(vacationId);
        VacationDto dto = VacationMapper.toDto(vacation);
        return ResponseEntity.ok(dto);
    }

    // PUT /api/vacations/{vacId}
    // Update a vacation
    @PutMapping("/user/{userId}")
    public ResponseEntity<VacationDto> updateVacation(
            @PathVariable Long userId,
            @Valid @RequestBody Vacation vacationBody) {
        User user = userService.getUserById(userId);
        Vacation updatedVacation = vacationService.updateVacation(vacationBody, user);
        VacationDto dto = VacationMapper.toDto(updatedVacation);
        return ResponseEntity.ok(dto);
    }
    // DELETE /api/vacations/{vacationId}
    // Delete a vacation
    @DeleteMapping("/{vacationId}")
    public ResponseEntity<?> deleteVacation(@PathVariable Long vacationId) {
        List<Excursion> excursions = vacationService.getExcursionsByVacationId(vacationId);
        Vacation vacation = vacationService.getVacationById(vacationId);

        if (!excursions.isEmpty()) {
            return ResponseEntity.badRequest().body("Vacation has excursions and cannot be deleted");
        } else {
            vacationService.deleteVacation(vacationId);
            return ResponseEntity.ok("Vacation " + vacation.getTitle() +  " has been deleted");
        }
    }
}
