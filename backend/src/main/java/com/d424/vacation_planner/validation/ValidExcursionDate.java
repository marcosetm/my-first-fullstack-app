package com.d424.vacation_planner.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExcursionDateValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidExcursionDate {
    String message() default "Excursion date must fall within the vacation period";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

