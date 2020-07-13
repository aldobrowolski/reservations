package com.exercise.hotel;

public class ReservationNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -358284861121615979L;

	public ReservationNotFoundException(Long id) {
		super("Reservation not found for the id " + id);
	}
	
	public ReservationNotFoundException(String message) {
		super(message);
	}
}
