package com.exercise.hotel.controllers;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.exercise.hotel.dto.RatingDto;

@RestController
public class RoomController {
	
	private DiscoveryClient discoveryClient;
		
	public RoomController(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}
	
	@RequestMapping("/ratings")	
	public RatingDto ratings() {
		RatingDto ratingDto = new RatingDto();
		String uri = discoveryClient.getInstances("ratings").get(0).getUri().toString();
		RestTemplate restTemplate = new RestTemplate();
		String rating = restTemplate.getForObject(uri + "/ratings", String.class);
		ratingDto.setRating(rating);
		return ratingDto;
	}

}