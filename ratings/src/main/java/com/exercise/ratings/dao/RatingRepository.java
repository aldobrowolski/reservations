package com.exercise.ratings.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.ratings.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
	
}
