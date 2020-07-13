package com.exercise.ratings;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.ratings.dao.RatingRepository;
import com.exercise.ratings.dto.RatingDto;
import com.exercise.ratings.model.Rating;

@RestController
public class RatingController {
	
	private RatingRepository ratingRepository;
	
	@Autowired
	public RatingController(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}
	
	@RequestMapping("/ratings")
	public List<RatingDto> ratings() {
		return ratingRepository.findAll().stream().map(this::toRatingDto).collect(Collectors.toList());
	}
	
	@RequestMapping("/ratings/{roomId}")
	public RatingDto ratingById(@PathVariable Long roomId) {
		Rating rating = ratingRepository.findRating(roomId);
		return toRatingDto(rating);
	}
		
	private RatingDto toRatingDto(Rating rating) {
		RatingDto dto = new RatingDto();
		dto.setRating(rating.getRating());
		dto.setRoomId(rating.getRoomId());
		return dto;
	}

}
