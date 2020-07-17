package com.exercise.hotel;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.exercise.hotel.dao.*;
import com.exercise.hotel.dto.ReservationDto;
import com.exercise.hotel.services.LinkService;
import com.exercise.hotel.services.ReservationHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest
public class ReservationControllerTest {

	@Autowired
	private MockMvc mockMvc;	
	
	@MockBean
	private RoomRepository roomRepository;
	
	@MockBean
	private ReservationRepository reservationRepository;

	@MockBean
	private LinkService linkService;
	
	@MockBean
	private ReservationHandler reservationHandler;
	
	@MockBean
	private RestTemplate restTemplate;	
		
	@Test
	public void testGetReservations() throws Exception {
        ReservationDto dto = new ReservationDto();
        dto.setUserName("Bob");        
        List<ReservationDto> reservations = new ArrayList<>();
        reservations.add(dto);
        
        when(reservationHandler.findAll()).thenReturn(reservations);
        
		mockMvc.perform(get("/reservations")).andDo(print()).andExpect(status().
				isOk()).andExpect(content().string(containsString("\"userName\":\"Bob\"")));
	}

	@Test
	public void testGetReservationsForRoom() throws Exception {
        ReservationDto dto = new ReservationDto();
        dto.setPeopleNumber(3);        
        List<ReservationDto> reservations = new ArrayList<>();
        reservations.add(dto);
        
        when(reservationHandler.findById(anyLong())).thenReturn(reservations);
        
		mockMvc.perform(get("/reservations")).andDo(print()).andExpect(status().
				isOk()).andExpect(content().string(containsString("\"peopleNumber\":\"3\"")));
	}
	
	@Test
	public void testGetReservationById() throws Exception {
        ReservationDto dto = new ReservationDto();
        dto.setEndDate(LocalDate.of(2020, 7, 30));
        
        when(reservationHandler.getReservation(anyLong())).thenReturn(dto);
        
		mockMvc.perform(get("/reservations/1")).andDo(print()).andExpect(status().
				isOk()).andExpect(content().string(containsString("\"endDate\":\"2020-07-30\"")));
	}
	
}
