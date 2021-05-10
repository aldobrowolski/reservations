package com.exercise.hotel;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.exercise.hotel.dao.*;
import com.exercise.hotel.dto.ReservationDto;
import com.exercise.hotel.model.Reservation;
import com.exercise.hotel.services.LinkService;
import com.exercise.hotel.services.ReservationService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest
public class ReservationControllerTest {
	
	private static final String CONTENT = "{\"userName\": \"Bob\", \"peopleNumber\": 3, \"startDate\": \"2021-05-06\", \"endDate\": \"2021-05-07\"}";

	private static final String LINK ="http://localhost:8080/reservations/4";

	@Autowired
	private MockMvc mockMvc;	
	
	@MockBean
	private RoomRepository roomRepository;
	
	@MockBean
	private ReservationRepository reservationRepository;

	@MockBean
	private LinkService linkService;
	
	@MockBean
	private ReservationService reservationService;
	
	@MockBean
	private RestTemplate restTemplate;
			
	@Test
	public void testGetReservations() throws Exception {
        ReservationDto dto = new ReservationDto();
        dto.setUserName("Bob");        
        List<ReservationDto> reservations = Collections.singletonList(dto);
        
        when(reservationService.findAll()).thenReturn(reservations);
        
		mockMvc.perform(get("/reservations")).andDo(print()).andExpect(status().
				isOk()).andExpect(content().string(containsString("\"userName\":\"Bob\"")));
	}

	@Test
	public void testGetReservationsForRoom() throws Exception {
        ReservationDto dto = new ReservationDto();
        dto.setPeopleNumber(3);        
        List<ReservationDto> reservations = Collections.singletonList(dto);
        
        when(reservationService.findByRoomId(anyLong())).thenReturn(reservations);
        
		mockMvc.perform(get("/reservations?roomNumber=1")).andDo(print()).andExpect(status().
				isOk()).andExpect(content().string(containsString("\"peopleNumber\":3")));
	}
	
	@Test
	public void testGetReservationById() throws Exception {
        ReservationDto dto = new ReservationDto();
        dto.setEndDate(LocalDate.of(2020, 7, 30));
        
        when(reservationService.getReservation(anyLong())).thenReturn(dto);
        
		mockMvc.perform(get("/reservations/1")).andDo(print()).andExpect(status().
				isOk()).andExpect(content().string(containsString("\"endDate\":\"2020-07-30\"")));
	}
	
	@Test
	public void testAddReservation() throws Exception {
		when(linkService.getReservationLink(any())).thenReturn(LINK);
		
		mockMvc.perform(post("/reservations")
				.content(CONTENT)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString(LINK)));
	}
	
	@Test
	public void testUpdateReservation() throws Exception {
		when(reservationService.saveReservation(any(), any()))
		.thenReturn(Reservation.builder().id(1L).build());
		
		when(linkService.getReservationLink(any())).thenReturn(LINK);
		
		mockMvc.perform(put("/reservations/1")
				.content(CONTENT)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString(LINK)));
	}
	
	@Test
	public void testDeleteReservation() throws Exception {
        ReservationDto dto = new ReservationDto();
        dto.setEndDate(LocalDate.of(2020, 7, 30));
        
        when(reservationService.getReservation(anyLong())).thenReturn(dto);
		when(linkService.getReservationsLink()).thenReturn(LINK);
        
		mockMvc.perform(delete("/reservations/1")).andDo(print()).andExpect(status().
				isOk()).andExpect(content().string(containsString(LINK)));
	}	
}
