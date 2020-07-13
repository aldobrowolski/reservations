package com.exercise.hotel.services;

import org.springframework.stereotype.Component;

import com.exercise.hotel.controllers.ReservationController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class LinkService {

	public String getReservationLink(Long id) {
		return linkTo(methodOn(ReservationController.class).reservation(id)).
				toUri().toString();
	}
	
	public String getReservationsLink() {
		return linkTo(methodOn(ReservationController.class).reservations((Long) null)).
				toUri().toString();
	}
	
}
