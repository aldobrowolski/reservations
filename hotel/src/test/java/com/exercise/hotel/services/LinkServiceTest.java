package com.exercise.hotel.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkServiceTest {
	
	@Autowired
	LinkService linkService;
	
	@Test
	public void testGetReservationLink() {
		String link = linkService.getReservationLink(1L);
		
		assertEquals("http://localhost/reservations/1", link);
	}
	
	@Test
	public void testGetReservationsLink() {
		String link = linkService.getReservationsLink();
		
		assertEquals("http://localhost/reservations", link);
	}	
}
