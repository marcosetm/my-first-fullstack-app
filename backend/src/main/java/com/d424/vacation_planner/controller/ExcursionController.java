package com.d424.vacation_planner.controller;

import com.d424.vacation_planner.dto.ExcursionDto;
import com.d424.vacation_planner.entity.Excursion;
import com.d424.vacation_planner.entity.Vacation;
import com.d424.vacation_planner.mapper.ExcursionMapper;
import com.d424.vacation_planner.service.ExcursionService;
import com.d424.vacation_planner.service.VacationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/excursions")
public class ExcursionController {

    private final ExcursionService excursionService;
    private final VacationService vacationService;

    @Autowired
    public ExcursionController(ExcursionService excursionService, VacationService vacationService) {
        this.excursionService = excursionService;
        this.vacationService = vacationService;
    }

    // POST /api/excursions/vacation/{vacationId}
    @PostMapping("/vacation/{vacationId}")
    public ResponseEntity<ExcursionDto> createExcursion(
            @PathVariable Long vacationId,
            @Valid @RequestBody Excursion excursionBody) {

        Vacation vacation = vacationService.getVacationById(vacationId);
        Excursion saved = excursionService.createExcursion(vacation, excursionBody);
        return ResponseEntity.ok(ExcursionMapper.toDto(saved));
    }

    // GET /api/excursions/vacation/{vacationId}
    // Get excursions by vacation
    @GetMapping("/vacation/{vacationId}")
    public ResponseEntity<List<ExcursionDto>> getExcursionsByVacation(@PathVariable Long vacationId) {
        List<ExcursionDto> excursions = excursionService.getExcursionsByVacationId(vacationId)
                .stream()
                .map(ExcursionMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(excursions);
    }

    // GET /api/excursions/{excursionId}
    // Get excursion details
    @GetMapping("/{excursionId}")
    public ResponseEntity<Excursion> getExcursionById(@PathVariable Long excursionId) {
        Excursion excursion = excursionService.getExcursionById(excursionId);
        return ResponseEntity.ok(excursion);
    }

    // PUT /api/excursions/vacation/{vacationId}
    @PutMapping("/vacation/{vacationId}")
    public ResponseEntity<ExcursionDto> updateExcursion(
            @PathVariable Long vacationId,
            @Valid @RequestBody Excursion excursionBody) {
        Vacation vacation = vacationService.getVacationById(vacationId);
        Excursion updated = excursionService.updateExcursion(vacation, excursionBody);
        return ResponseEntity.ok(ExcursionMapper.toDto(updated));
    }
    // DELETE /api/excursions/{excursionId}
    @PutMapping("/{excursionId}")
    public ResponseEntity<String> deleteExcursion(@PathVariable Long excursionId) {
        Excursion excursion = excursionService.getExcursionById(excursionId);
        excursionService.deleteExcursionById(excursionId);
        return ResponseEntity.ok("Excursion " + excursion.getName() + " has been deleted");
    }
}
