package com.exercise.hotel.services;

import static com.exercise.hotel.model.RoomType.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import com.exercise.hotel.ReservationNotFoundException;
import com.exercise.hotel.dao.*;
import com.exercise.hotel.dto.ReservationDto;
import com.exercise.hotel.model.*;

@Component
public class ReservationService {

	private ReservationRepository reservationRepository;
	
	private RoomRepository roomRepository;
	
	public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository) {
		this.reservationRepository = reservationRepository;
		this.roomRepository = roomRepository;
	}
	
	public List<ReservationDto> findAll() {
		return reservationRepository.findAll().stream().map(this::mapToReservationDto).
		collect(Collectors.toList());
	}
	
	public List<ReservationDto> findById(Long id) {
		return reservationRepository.findByRoomId(id).stream().map(this::mapToReservationDto).
				collect(Collectors.toList());		
	}
	
	public Reservation saveReservation(Long id, ReservationDto dto) {
		Room room = findRoom(id, dto);
		validateGuestsNumber(dto, room);		
		validateReservationAvailibility(id, dto, room);
		Reservation reservation = updateReservation(dto, id);		
		reservation.setRoom(room);
		return reservationRepository.save(reservation);		
	}
			
	public void deleteReservation(Long id) {
		if (reservationRepository.existsById(id)) {
			reservationRepository.deleteById(id);
		}		
	}
	
	public ReservationDto getReservation(Long id) {
		return mapToReservationDto(findReservation(id));
	}
				
	public Long addReservation(ReservationDto dto) {
		RoomType type = selectRoomType(dto.getPeopleNumber());
		List<Room> rooms = roomRepository.findRoom(type, dto.getStartDate(), dto.getEndDate());
		if (rooms.isEmpty()) {
			throw new ReservationNotFoundException("No free rooms");
		}
		Reservation reservation = newReservation(dto, rooms.get(0));
		return reservationRepository.save(reservation).getId();		
	}
	
	private ReservationDto mapToReservationDto(Reservation reservation) {
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
	
	private Reservation updateReservation(ReservationDto dto, Long id) {
		Reservation reservation = reservationRepository.findById(id).
				orElseThrow(() -> new ReservationNotFoundException(id));
		Optional.ofNullable(dto.getUserName()).ifPresent(userName -> reservation.setUserName(userName));
		Optional.ofNullable(dto.getPeopleNumber()).ifPresent(number -> reservation.setPeopleNumber(number));
		Optional.ofNullable(dto.getStartDate()).ifPresent(date -> reservation.setStartDate(date));
		Optional.ofNullable(dto.getEndDate()).ifPresent(date -> reservation.setEndDate(date));
		return reservation;
	}
	
	private RoomType selectRoomType(Integer count) {
		if (count == null || count > PENTHOUSE.getMaxPeople()) {
			throw new ReservationNotFoundException("Number of people not specified or greater than maximal of " + PENTHOUSE + '.');
		}		
		if (count <= BASIC.getMaxPeople()) {
			return BASIC;
		}
		if (count <= SUITE.getMaxPeople()) {
			return SUITE; 
		}
		return PENTHOUSE;
	}	
	
	private Reservation newReservation(ReservationDto dto, Room room) {
		Reservation reservation = new Reservation();
		reservation.setRoom(room);
		reservation.setUserName(dto.getUserName());
		reservation.setPeopleNumber(dto.getPeopleNumber());
		reservation.setStartDate(dto.getStartDate());
		reservation.setEndDate(dto.getEndDate());
		return reservation;
	}
	
	private boolean isReservationAvailable(Long id, ReservationDto dto, Long roomId) {
		return reservationRepository.findConflictingReservations(id,
				dto.getStartDate(), dto.getEndDate(), roomId).isEmpty();		
	}
	
	private Room findRoom(Long reservationId, ReservationDto dto) {
		Reservation reservation = findReservation(reservationId);
		Room reservationRoom = reservation.getRoom();
		if (dto.getRoomId() == null) {
			return reservationRoom;
		}
		return roomRepository.findById(dto.getRoomId()).
				orElseThrow(() -> new ReservationNotFoundException("No room with the id: " + dto.getRoomId()));
	}	
	
	private Reservation findReservation(Long id) {
		return reservationRepository.findById(id).
				orElseThrow(() -> new ReservationNotFoundException(id));
	}
	
	private void validateReservationAvailibility(Long id, ReservationDto dto, Room room) {
		if (!isReservationAvailable(id, dto, room.getId())) {
			throw new ReservationNotFoundException("Reservation is unavailable for the given time period.");
		}
	}

	private void validateGuestsNumber(ReservationDto dto, Room room) {
		if (dto.getPeopleNumber() == null) {
			return;
		}
		if (dto.getPeopleNumber() > room.getType().getMaxPeople()) {
			throw new ReservationNotFoundException("The maximum number of people for " + 
		    room.getType() + " is " + room.getType().getMaxPeople());
		}
	}	
}