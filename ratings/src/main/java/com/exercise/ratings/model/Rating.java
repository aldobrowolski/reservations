package com.exercise.ratings.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Rating {

	@Id
	@GeneratedValue	
	private Long id;
	
	private String rating;
	
	private Long roomId;
}
