package com.exercise.hotel.model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Room {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private RoomType type;
	
}
