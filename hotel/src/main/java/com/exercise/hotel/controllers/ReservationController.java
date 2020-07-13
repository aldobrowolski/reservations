package com.exercise.hotel.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.exercise.hotel.ReservationNotFoundException;
import com.exercise.hotel.dao.*;
import com.exercise.hotel.dto.ReservationDto;
import com.exercise.hotel.model.*;
import com.exercise.hotel.services.*;

import static com.exercise.hotel.model.RoomType.*;

@RestController
public class ReservationController {

	private RoomRepository roomRepository;
	
	private ReservationRepository reservationRepository;
	
	private LinkService linkService;
	
	private ReservationMapper reservationMapper;
	
	public ReservationController(RoomRepository roomRepository, ReservationRepository reservationRepository,
			LinkService linkService, ReservationMapper reservationMapper) {
		this.roomRepository = roomRepository;
		this.reservationRepository = reservationRepository;
		this.linkService = linkService;
		this.reservationMapper = reservationMapper;
	}
	
	@GetMapping("/reservations")
	public List<ReservationDto> reservations(@RequestParam(name = "roomNumber", required = false) Long id) {
		if (id == null) {
		return reservationRepository.findAll().stream().map(reservationMapper::mapToReservationDto).
				collect(Collectors.toList());
		}
		return reservationRepository.findByRoomId(id).stream().map(reservationMapper::mapToReservationDto).
				collect(Collectors.toList());		
	}

	@GetMapping("/reservations/{id}")
	public ReservationDto reservation(@PathVariable Long id) {
		Reservation reservation = findReservation(id);
		return reservationMapper.mapToReservationDto(reservation);
	}
	
	@PutMapping("/reservations/{id}")	
	public String updateReservation(@PathVariable Long id, @Valid @RequestBody ReservationDto dto) {
		Room room = findRoom(id, dto);
		if (dto.getPeopleNumber() > room.getType().getMaxPeople()) {
			throw new ReservationNotFoundException("The maximum number of people for " + 
		    room.getType() + " is " + room.getType().getMaxPeople());
		}		
		if (!isReservationAvailable(id, dto, room.getId())) {
			return "Reservation is unavailable for the given time period.";
		}
		Reservation reservation = reservationMapper.mapToReservationEntity(dto, id);		
		reservation.setRoom(room);
		reservation = reservationRepository.save(reservation);
		return linkService.getReservationLink(reservation.getId());
	}
	
	@DeleteMapping("/reservations/{id}")	
	public String deleteReservation(@PathVariable Long id) {
		if (reservationRepository.existsById(id)) {
			reservationRepository.deleteById(id);
		}
		return linkService.getReservationsLink();
	}
	
	@PostMapping("/reservations")
	public String addReservation(@Valid @RequestBody ReservationDto dto) {
		RoomType type = selectRoomType(dto.getPeopleNumber());
		List<Room> rooms = roomRepository.findRoom(type, dto.getStartDate(), dto.getEndDate());
		if (rooms.isEmpty()) {
			return "No free rooms";
		}
		Reservation reservation = newReservation(dto, rooms.get(0));
		Long id = reservationRepository.save(reservation).getId();
		return linkService.getReservationLink(id);
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

	private RoomType selectRoomType(Integer count) {
		if (count == null || count > PENTHOUSE.getMaxPeople()) {
			throw new ReservationNotFoundException("Number of people not specified or greater than maximal of " + PENTHOUSE + '.');
		}		
		if (count <= BASIC.getMaxPeople()) {
			return BASIC;
		}
		else if (count <= SUITE.getMaxPeople()) {
			return SUITE; 
		}
		return PENTHOUSE;
	}
	
	private Room findRoom(Long reservationId, ReservationDto dto) {
		Room reservationRoom = findReservation(reservationId).getRoom();
		if (dto.getRoomId() == null) {
			return reservationRoom;
		}
		return roomRepository.findById(dto.getRoomId()).
				orElse(reservationRoom);
	}

	private boolean isReservationAvailable(Long id, ReservationDto dto, Long roomId) {
		return reservationRepository.findConflictingReservations(id,
				dto.getStartDate(), dto.getEndDate(), roomId).isEmpty();		
	}

	private Reservation findReservation(Long id) {
		return reservationRepository.findById(id).
				orElseThrow(() -> new ReservationNotFoundException(id));
	}
	
}
