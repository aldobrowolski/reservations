package com.exercise.hotel.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Reservation {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String userName;
	
	private int peopleNumber;

	private LocalDate startDate;
	
	private LocalDate endDate;

	@ManyToOne
	private Room room;
}
