package com.exercise.hotel;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.exercise.hotel.model.*;
import com.exercise.hotel.dao.*;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private RoomRepository roomRepository;

	private ReservationRepository reservationRepository;

	@Autowired
	public DatabaseLoader(RoomRepository roomRepository, 
			ReservationRepository reservationRepository) {
		this.roomRepository = roomRepository;
		this.reservationRepository = reservationRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Room room = newRoom(RoomType.BASIC);
		roomRepository.save(room);
		room = roomRepository.findById(room.getId()).get();
		Reservation reservation = newReservation(room, "Bob", 3, LocalDate.now(), LocalDate.of(2021, 5, 30));
		reservation = reservationRepository.save(reservation);
		room = newRoom(RoomType.BASIC);
		roomRepository.save(room);		
		System.out.println("Zapisano encje.");
	}

	private Room newRoom(RoomType roomType) {
		Room room = new Room();
		room.setType(roomType);
		return room;
	}

	private Reservation newReservation(Room room, String userName, int peopleNumber, 
			LocalDate startDate, LocalDate endDate) {
		Reservation reservation = new Reservation();
		reservation.setRoom(room);
		reservation.setUserName(userName);
		reservation.setPeopleNumber(peopleNumber);
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		return reservation;
	}

}
