package com.exercise.hotel;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.exercise.hotel.dao.*;
import com.exercise.hotel.dto.ReservationDto;
import com.exercise.hotel.model.Reservation;
import com.exercise.hotel.model.Room;
import com.exercise.hotel.model.RoomType;
import com.exercise.hotel.services.LinkService;
import com.exercise.hotel.services.ReservationMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;

@WebMvcTest
public class HotelControllerTest {

	/*@Autowired
	private MockMvc mockMvc;	
	
	@MockBean
	private RoomRepository roomRepository;
	
	@MockBean
	private ReservationRepository reservationRepository;

	@MockBean
	private LinkService linkService;
	
	@MockBean
	private ReservationMapper reservationMapper;
		
	@Test
	public void testGetReservations() throws Exception {
		Reservation reservation = new Reservation();
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        when(reservationRepository.findAll()).thenReturn(reservations);
        ReservationDto dto = new ReservationDto();
        dto.setUserName("Bob");
        when(reservationMapper.mapToReservationDto(reservation)).thenReturn(dto);
        
		mockMvc.perform(get("/reservations")).andDo(print()).andExpect(status().
				isOk()).andExpect(content().string(containsString("\"userName\":\"Bob\"")));
	}*/
		
}
