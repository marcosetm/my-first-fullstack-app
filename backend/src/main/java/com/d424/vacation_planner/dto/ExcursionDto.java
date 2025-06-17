package com.d424.vacation_planner.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExcursionDto {
    private Long id;
    private String name;
    private LocalDate startDate;
    private Long vacationId;

    // Constructors
    public ExcursionDto() {}

    public ExcursionDto(Long id, String name, LocalDate startDate, Long vacationId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.vacationId = vacationId;
    }
}
