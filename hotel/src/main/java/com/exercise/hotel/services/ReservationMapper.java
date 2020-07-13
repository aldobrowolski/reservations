package com.exercise.hotel.services;

import org.springframework.stereotype.Component;

import com.exercise.hotel.ReservationNotFoundException;
import com.exercise.hotel.dao.ReservationRepository;
import com.exercise.hotel.dto.ReservationDto;
import com.exercise.hotel.model.*;

@Component
public class ReservationMapper {

	private ReservationRepository reservationRepository;
	
	public ReservationMapper(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}
	
	public ReservationDto mapToReservationDto(Reservation reservation) {
		Room room = reservation.getRoom();
		ReservationDto dto = new ReservationDto();
		dto.setId(reservation.getId());
		dto.setUserName(reservation.getUserName());
		dto.setPeopleNumber(reservation.getPeopleNumber());
		dto.setStartDate(reservation.getStartDate());
		dto.setEndDate(reservation.getEndDate());
		dto.setRoomId(room.getId());
		dto.setType(room.getType());
		return dto;
	}
	
	public Reservation mapToReservationEntity(ReservationDto dto, Long id) {
		Reservation reservation = reservationRepository.findById(id).
				orElseThrow(() -> new ReservationNotFoundException(dto.getId()));
		reservation.setUserName(dto.getUserName());
		reservation.setPeopleNumber(dto.getPeopleNumber());
		reservation.setStartDate(dto.getStartDate());
		reservation.setEndDate(dto.getEndDate());
		return reservation;
	}
		
}
