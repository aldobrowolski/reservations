package com.exercise.hotel;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class ReservationNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(ReservationNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)	
	public String handle(ReservationNotFoundException ex) {
		return ex.getMessage();
	}
}
