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
	
	private ReservationService reservationService;
	
	public ReservationController(LinkService linkService, ReservationService reservationMapper) {
		this.linkService = linkService;
		this.reservationService = reservationMapper;
	}
	
	@GetMapping("/reservations")
	public List<ReservationDto> reservations(@RequestParam(name = "roomNumber", required = false) Long id) {
		if (id == null) {
		return reservationService.findAll();
		}
		return reservationService.findById(id);		
	}

	@GetMapping("/reservations/{id}")
	public ReservationDto reservation(@PathVariable Long id) {
		return reservationService.getReservation(id);
	}
	
	@PutMapping("/reservations/{id}")	
	public String updateReservation(@PathVariable Long id, @RequestBody ReservationDto dto) {
		Reservation reservation = reservationService.saveReservation(id, dto);
		return linkService.getReservationLink(reservation.getId());
	}
	
	@DeleteMapping("/reservations/{id}")	
	public String deleteReservation(@PathVariable Long id) {
		reservationService.deleteReservation(id);
		return linkService.getReservationsLink();
	}
	
	@PostMapping("/reservations")
	public String addReservation(@Valid @RequestBody ReservationDto dto) {
		Long id = reservationService.addReservation(dto);
		return linkService.getReservationLink(id);
	}	
}

