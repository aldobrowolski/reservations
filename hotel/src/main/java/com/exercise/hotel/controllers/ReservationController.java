package com.exercise.hotel.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.exercise.hotel.dto.ReservationDto;
import com.exercise.hotel.model.*;
import com.exercise.hotel.services.*;

@RestController
public class ReservationController {
	
	private LinkService linkService;
	
	private ReservationHandler reservationHandler;
	
	public ReservationController(LinkService linkService, ReservationHandler reservationMapper) {
		this.linkService = linkService;
		this.reservationHandler = reservationMapper;
	}
	
	@GetMapping("/reservations")
	public List<ReservationDto> reservations(@RequestParam(name = "roomNumber", required = false) Long id) {
		if (id == null) {
		return reservationHandler.findAll();
		}
		return reservationHandler.findById(id);		
	}

	@GetMapping("/reservations/{id}")
	public ReservationDto reservation(@PathVariable Long id) {
		return reservationHandler.getReservation(id);
	}
	
	@PutMapping("/reservations/{id}")	
	public String updateReservation(@PathVariable Long id, @Valid @RequestBody ReservationDto dto) {
		Reservation reservation = reservationHandler.saveReservation(id, dto);
		return linkService.getReservationLink(reservation.getId());
	}
	
	@DeleteMapping("/reservations/{id}")	
	public String deleteReservation(@PathVariable Long id) {
		reservationHandler.deleteReservation(id);
		return linkService.getReservationsLink();
	}
	
	@PostMapping("/reservations")
	public String addReservation(@Valid @RequestBody ReservationDto dto) {
		Long id = reservationHandler.addReservation(dto);
		return linkService.getReservationLink(id);
	}	
}
