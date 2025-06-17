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

    // POST /api/excursions?vacationId=1
    @PostMapping
    public ResponseEntity<ExcursionDto> createExcursion(
            @RequestParam Long vacationId,
            @Valid @RequestBody Excursion excursionRequest) {

        Vacation vacation = vacationService.getVacationById(vacationId);
        Excursion saved = excursionService.createExcursion(vacation, excursionRequest);
        return ResponseEntity.ok(ExcursionMapper.toDto(saved));
    }

    // GET /api/excursions?vacationId=1
    @GetMapping
    public ResponseEntity<List<ExcursionDto>> getExcursionsByVacation(@RequestParam Long vacationId) {
        List<ExcursionDto> excursions = excursionService.getExcursionsByVacationId(vacationId)
                .stream()
                .map(ExcursionMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(excursions);
    }
}
