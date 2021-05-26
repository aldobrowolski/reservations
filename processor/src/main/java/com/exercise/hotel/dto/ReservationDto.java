package com.exercise.hotel.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.exercise.hotel.model.RoomType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6128265808219023718L;

	private Long id;
	
	private String userName;
	
	private Integer peopleNumber;

	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private Long roomId;
	
	private RoomType type;

}
