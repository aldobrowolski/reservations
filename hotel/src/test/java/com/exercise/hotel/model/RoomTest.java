package com.exercise.hotel.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RoomTest {

	private static final RoomType TYPE = RoomType.BASIC;
	private static final long ID = 1L;
	private static final String BUILDER_OBJECT = "Room.RoomBuilder(id=1, type=BASIC)";

	@Test
	public void testCompareRoom() {
		Room.RoomBuilder roomBuilder = Room.builder()
				.id(ID)
				.type(TYPE);
		Room firstRoom = roomBuilder.build();
		Room secondRoom = new Room();
		secondRoom.setId(ID);
		secondRoom.setType(TYPE);
		
		assertEquals(true, firstRoom.equals(secondRoom));
		assertEquals(true, firstRoom.equals(firstRoom));
		assertEquals(firstRoom.toString(), secondRoom.toString());
		assertEquals(firstRoom.hashCode(), secondRoom.hashCode());
		secondRoom.setId(null);
		assertEquals(false, firstRoom.equals(secondRoom));
		assertEquals(false, firstRoom.equals(null));
		assertEquals(BUILDER_OBJECT, roomBuilder.toString());
	}
}
