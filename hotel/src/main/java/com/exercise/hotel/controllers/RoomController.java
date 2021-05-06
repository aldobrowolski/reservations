package com.exercise.hotel.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.exercise.hotel.dto.RatingDto;

@RestController
public class RoomController {

	private static final String SINGLE_RATING_URI = "//ratings/ratings/{roomId}";
	private static final String ALL_RATINGS_URI = "//ratings/ratings";
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/ratings")
	public List<RatingDto> ratings() {
		String uri = UriComponentsBuilder.fromUriString(ALL_RATINGS_URI).
				build().toString();
		RatingDto[] ratings = restTemplate.getForEntity(uri, RatingDto[].class).getBody();
		return Arrays.asList(ratings);
	}

	@RequestMapping("/ratings/{roomId}")
	public RatingDto rating(@PathVariable Long roomId) {
		String uri = UriComponentsBuilder.fromUriString(SINGLE_RATING_URI).
				build(roomId).toString();
		return restTemplate.getForObject(uri, RatingDto.class);
	}
}
