package com.exercise.processor.services;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.exercise.hotel.dto.ReservationDto;
import com.exercise.processor.dao.ReservationRepository;
import com.exercise.processor.entity.Reservation;
import com.exercise.processor.ReservationException;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReservationService {
	
	private final ReservationRepository reservationRepository;

	public void updateReservation(ReservationDto dto) {
		Reservation reservation = findReservation(dto.getId());
		updateReservation(reservation, dto);
		reservationRepository.save(reservation);
	}
	
	private Reservation updateReservation(Reservation reservation, ReservationDto dto) {
		Optional.ofNullable(dto.getUserName()).ifPresent(userName -> reservation.setUserName(userName));
		Optional.ofNullable(dto.getPeopleNumber()).ifPresent(number -> reservation.setPeopleNumber(number));
		Optional.ofNullable(dto.getStartDate()).ifPresent(date -> reservation.setStartDate(date));
		Optional.ofNullable(dto.getEndDate()).ifPresent(date -> reservation.setEndDate(date));
		return reservation;
	}
	
	private Reservation findReservation(Long id) {
		return reservationRepository.findById(id).
				orElseThrow(() -> new ReservationException("Reservation not found"));
	}	
}
