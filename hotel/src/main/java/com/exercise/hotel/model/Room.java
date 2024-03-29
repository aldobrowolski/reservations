package com.exercise.hotel.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rooms")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {
	
	@Id
	@SequenceGenerator(name = "room_seq", sequenceName = "room_seq", allocationSize = 1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "room_seq")
	private Long id;
	
	private RoomType type;
	
}
