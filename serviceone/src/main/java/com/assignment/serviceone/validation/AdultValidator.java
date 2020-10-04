package com.assignment.serviceone.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {
    private static final int ADULT_AGE = 18;


    @Override
    public boolean isValid(LocalDate dob, ConstraintValidatorContext constraintValidatorContext) {
        return dob != null && LocalDate.now().minusYears(ADULT_AGE).isAfter(dob);
    }
}
