package com.exercise.hotel.dto;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.*;

@Documented
@Constraint(validatedBy = ReservationValidator.class)
@Retention(RUNTIME)
@Target({ TYPE })
public @interface ReservationConstraint {
	
    String message() default "Invalid reservation params.";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
