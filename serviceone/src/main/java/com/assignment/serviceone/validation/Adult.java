package com.assignment.serviceone.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Constraint(validatedBy = AdultValidator.class)
public @interface Adult {
    String message() default "Age as per DOB can't be less than 18";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
