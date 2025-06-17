package com.d424.vacation_planner.validation;

import com.d424.vacation_planner.entity.Excursion;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExcursionDateValidator implements ConstraintValidator<ValidExcursionDate, Excursion> {

    @Override
    public boolean isValid(Excursion excursion, ConstraintValidatorContext context) {
        if (excursion.getStartDate() == null || excursion.getVacation() == null) {
            return true; // Let @NotNull handle null values
        }

        var vacStart = excursion.getVacation().getStartDate();
        var vacEnd = excursion.getVacation().getEndDate();

        if (vacStart == null || vacEnd == null) {
            return true; // Vacation date range incomplete
        }

        return !excursion.getStartDate().isBefore(vacStart) &&
                !excursion.getStartDate().isAfter(vacEnd);
    }
}