package com.exercise.hotel.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.exercise.hotel.model.Room;
import com.exercise.hotel.model.RoomType;

public interface RoomRepository extends JpaRepository<Room, Long> {
	
	@Query("SELECT r from Room r WHERE type = :type AND id NOT IN "
			+ "(SELECT b.room.id FROM Reservation b WHERE b.startDate BETWEEN :start AND :end"
			+ " OR b.endDate BETWEEN :start AND :end)")
	List<Room> findRoom(@Param("type") RoomType type, @Param("start") LocalDate start, @Param("end") LocalDate end);
}
