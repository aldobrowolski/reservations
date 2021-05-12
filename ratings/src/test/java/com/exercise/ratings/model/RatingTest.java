package com.exercise.ratings.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RatingTest {
	
	private static final String RATING = "1";
	private static final long ROOM_ID = 1L;

	@Test
	public void testCompareRatingDto() {
		Rating firstRating = new Rating();
		firstRating.setId(ROOM_ID);
		firstRating.setRating(RATING);
		Rating secondRating = new Rating();
		secondRating.setId(ROOM_ID);
		secondRating.setRating(RATING);
		
		assertEquals(true, firstRating.equals(secondRating));
		assertEquals(true, firstRating.equals(firstRating));
		assertEquals(firstRating.toString(), secondRating.toString());
		assertEquals(firstRating.hashCode(), secondRating.hashCode());
		secondRating.setId(null);
		assertEquals(false, firstRating.equals(secondRating));
		assertEquals(false, firstRating.equals(null));
	}
}
