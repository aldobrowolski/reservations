package com.exercise.hotel.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exercise.hotel.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("SELECT r FROM Reservation r where r.room.id = :id")
	List<Reservation> findByRoomId(@Param("id") Long id);
	
	@Query("SELECT r FROM Reservation r where r.room.id = :roomId AND r.id <> :id"
			+ " AND (r.startDate BETWEEN :start AND :end"
			+ " OR r.endDate BETWEEN :start AND :end)")
	List<Reservation> findConflictingReservations(@Param("id") Long id, @Param("start") LocalDate start,
			@Param("end") LocalDate end, @Param("roomId") Long roomId);
}
