package com.d424.vacation_planner.mapper;

import com.d424.vacation_planner.dto.VacationDto;
import com.d424.vacation_planner.entity.Vacation;

public class VacationMapper {

    public static VacationDto toDto(Vacation vacation) {
        return new VacationDto(
                vacation.getId(),
                vacation.getTitle(),
                vacation.getStartDate(),
                vacation.getEndDate(),
                vacation.getUser().getId(),
                vacation.getExcursions() != null ? vacation.getExcursions().size() : 0
        );
    }

    public static Vacation toEntity(VacationDto dto) {
        Vacation vacation = new Vacation();
        vacation.setId(dto.getId());
        vacation.setTitle(dto.getTitle());
        vacation.setStartDate(dto.getStartDate());
        vacation.setEndDate(dto.getEndDate());
        vacation.getUser().setId(dto.getUserId());
        return vacation;
    }
}
