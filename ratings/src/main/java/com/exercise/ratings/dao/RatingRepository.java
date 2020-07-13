package com.exercise.ratings.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exercise.ratings.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
	
	@Query("SELECT r from Rating r WHERE roomId = :roomId")	
	Rating findRating(@Param("roomId") long roomId);

}
