package com.exercise.hotel.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private RoomType type;
	
}
