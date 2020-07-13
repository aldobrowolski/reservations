package com.exercise.hotel.dto;

import java.time.LocalDate;

import com.exercise.hotel.model.RoomType;

import lombok.Data;

@Data
@ReservationConstraint
public class ReservationDto {
	
	private Long id;
	
	private String userName;
	
	private Integer peopleNumber;

	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private Long roomId;
	
	private RoomType type;

}
