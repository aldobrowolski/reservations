package com.exercise.hotel.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class ReservationTest {
	
	private static final long ID = 1L;
	private static final LocalDate END_DATE = LocalDate.of(2020, 7, 30);
	private static final LocalDate START_DATE = LocalDate.of(2020, 7, 29);
	private static final int PEOPLE_NUMBER = 3;
	private static final String USER_NAME = "userName";
	private static final String BUILDER_OBJECT = "Reservation.ReservationBuilder(id=1, userName=userName, peopleNumber=3, startDate=2020-07-29, endDate=2020-07-30, room=null)";

	@Test
	public void testCompareReservation() {
		Reservation.ReservationBuilder reservationBuilder = Reservation.builder();
		Reservation firstReservation = reservationBuilder
				.id(ID)
				.userName(USER_NAME)
				.peopleNumber(PEOPLE_NUMBER)
				.startDate(START_DATE)
				.endDate(END_DATE)				
				.build();
		Reservation secondReservation = new Reservation();
		secondReservation.setId(ID);
		secondReservation.setUserName(USER_NAME);
		secondReservation.setPeopleNumber(PEOPLE_NUMBER);
		secondReservation.setStartDate(START_DATE);
		secondReservation.setEndDate(END_DATE);
		
		assertEquals(true, firstReservation.equals(secondReservation));
		assertEquals(true, firstReservation.equals(firstReservation));
		assertEquals(firstReservation.toString(), secondReservation.toString());
		assertEquals(firstReservation.hashCode(), secondReservation.hashCode());
		secondReservation.setUserName(null);
		assertEquals(false, firstReservation.equals(secondReservation));
		assertEquals(false, firstReservation.equals(null));
		assertEquals(BUILDER_OBJECT, reservationBuilder.toString());
	}
}
