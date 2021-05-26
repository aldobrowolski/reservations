package com.exercise.processor.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservations")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
	
	@Id
	@SequenceGenerator(name = "reservation_seq", sequenceName = "reservation_seq", allocationSize = 1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "reservation_seq")
	private Long id;
	
	private String userName;
	
	private int peopleNumber;

	private LocalDate startDate;
	
	private LocalDate endDate;

	@ManyToOne
	private Room room;
}
