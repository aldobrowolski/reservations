package com.exercise.ratings.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RatingDtoTest {
	
	private static final String RATING = "1";
	private static final long ROOM_ID = 1L;

	@Test
	public void testCompareRatingDto() {
		RatingDto firstDto = new RatingDto();
		firstDto.setRoomId(ROOM_ID);
		firstDto.setRating(RATING);
		RatingDto secondDto = new RatingDto();
		secondDto.setRoomId(ROOM_ID);
		secondDto.setRating(RATING);
		
		assertEquals(true, firstDto.equals(secondDto));
		assertEquals(true, firstDto.equals(firstDto));
		assertEquals(firstDto.toString(), secondDto.toString());
		assertEquals(firstDto.hashCode(), secondDto.hashCode());
		secondDto.setRoomId(null);
		assertEquals(false, firstDto.equals(secondDto));
		assertEquals(false, firstDto.equals(null));
	}
}
