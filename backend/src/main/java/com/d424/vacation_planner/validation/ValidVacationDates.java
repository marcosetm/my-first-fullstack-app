package com.d424.vacation_planner.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = VacationDatesValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidVacationDates {

    // https://www.baeldung.com/spring-mvc-custom-validator

    String message() default "End date must be after or equal to start date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

