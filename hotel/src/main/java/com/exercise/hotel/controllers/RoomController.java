package com.exercise.hotel.controllers;

import java.util.Arrays;
import java.util.List;

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
	public List<RatingDto> ratings() {
		String uri = discoveryClient.getInstances("ratings").get(0).getUri().toString();
		RestTemplate restTemplate = new RestTemplate();
		RatingDto[] ratings = restTemplate.getForEntity(uri + "/ratings", RatingDto[].class).getBody();
		return Arrays.asList(ratings);
	}

	@RequestMapping("/ratings/{roomId}")	
	public RatingDto rating(@PathVariable Long roomId) {
		String uri = discoveryClient.getInstances("ratings").get(0).getUri().toString();
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(uri + "/ratings/" + roomId, RatingDto.class);
	}
	
}