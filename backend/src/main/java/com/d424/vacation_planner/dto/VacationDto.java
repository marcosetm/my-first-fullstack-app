package com.d424.vacation_planner.dto;

import com.d424.vacation_planner.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VacationDto {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId;
    private int excursionCount;

    // Constructors
    public VacationDto() {}

    public VacationDto(Long id, String title, LocalDate startDate, LocalDate endDate, Long userId, Integer excursionCount) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.excursionCount = excursionCount;
    }
}
