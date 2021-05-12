package com.exercise.hotel.dto;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import com.exercise.hotel.model.RoomType;

public class ReservationDtoTest {
	
	private static final RoomType ROOM_TYPE = RoomType.BASIC;
	private static final long ROOM_ID = 2L;
	private static final LocalDate END_DATE = LocalDate.of(2020, 7, 30);
	private static final LocalDate START_DATE = LocalDate.of(2020, 7, 29);
	private static final int PEOPLE_NUMBER = 3;
	private static final String USER_NAME = "userName";
	private static final long ID = 1L;
	private static final String BUILDER_OBJECT = "ReservationDto.ReservationDtoBuilder(id=1, userName=userName, peopleNumber=3, startDate=2020-07-29, endDate=2020-07-30, roomId=2, type=BASIC)";

	@Test
	public void testCompareReservationDto() {		
		ReservationDto.ReservationDtoBuilder dtoBuilder = ReservationDto.builder()
		.id(ID)
		.userName(USER_NAME)
		.peopleNumber(PEOPLE_NUMBER)
		.startDate(START_DATE)
		.endDate(END_DATE)
		.roomId(ROOM_ID)
		.type(ROOM_TYPE);
		ReservationDto firstDto = dtoBuilder.build();	
		
		ReservationDto secondDto = new ReservationDto();
		secondDto.setId(ID);
		secondDto.setUserName(USER_NAME);
		secondDto.setPeopleNumber(PEOPLE_NUMBER);
		secondDto.setStartDate(START_DATE);
		secondDto.setEndDate(END_DATE);
		secondDto.setRoomId(ROOM_ID);
		secondDto.setType(ROOM_TYPE);
		
		assertEquals(true, firstDto.equals(secondDto));
		assertEquals(true, firstDto.equals(firstDto));
		assertEquals(firstDto.toString(), secondDto.toString());
		assertEquals(firstDto.hashCode(), secondDto.hashCode());
		secondDto.setUserName(null);
		assertEquals(false, firstDto.equals(secondDto));
		assertEquals(false, firstDto.equals(null));
		assertEquals(BUILDER_OBJECT, dtoBuilder.toString());
	}
}
