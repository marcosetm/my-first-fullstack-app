package com.d424.vacation_planner.mapper;

import com.d424.vacation_planner.dto.ExcursionDto;
import com.d424.vacation_planner.entity.Excursion;

public class ExcursionMapper {

    public static ExcursionDto toDto(Excursion excursion) {
        return new ExcursionDto(
                excursion.getId(),
                excursion.getName(),
                excursion.getDate(),
                excursion.getVacation().getId()
        );
    }

    public static Excursion toEntity(ExcursionDto excursionDto) {
        Excursion excursion = new Excursion();
        excursion.setId(excursionDto.getId());
        excursion.setName(excursionDto.getName());
        excursion.setDate(excursionDto.getDate());
        excursion.setId(excursionDto.getVacationId());
        return excursion;
    }
}
