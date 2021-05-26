package com.exercise.hotel.model;

public enum RoomType {
	
	BASIC(4), SUITE(6), PENTHOUSE(8);
	
	private int max;
	
	RoomType(int max) {
		this.max = max;
	}
	
	public int getMaxPeople() {
		return max;
	}
}
