package com.exercise.hotel.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ReservationValidator implements ConstraintValidator<ReservationConstraint, ReservationDto> {

	@Override
	public boolean isValid(ReservationDto value, ConstraintValidatorContext context) {
		return value.getStartDate() != null && value.getEndDate() != null &&
			   !value.getEndDate().isBefore(value.getStartDate());
	}
}