package com.exercise.ratings.model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "ratings")
@Data
public class Rating {

	@Id
	private Long id;
	
	@Column(name = "rating")
	private String rating;
	
}
