package com.d424.vacation_planner.validation;

import com.d424.vacation_planner.entity.Vacation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VacationDatesValidator implements ConstraintValidator<ValidVacationDates, Vacation> {

    // https://docs.spring.io/spring-framework/reference/core/validation/validator.html

    @Override
    public boolean isValid(Vacation vacation, ConstraintValidatorContext context) {
        if (vacation.getStartDate() == null || vacation.getEndDate() == null) {
            return true; // Let @NotNull handle this
        }

        return !vacation.getEndDate().isBefore(vacation.getStartDate());
    }
}