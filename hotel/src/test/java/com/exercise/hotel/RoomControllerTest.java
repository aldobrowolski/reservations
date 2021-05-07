package com.exercise.hotel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.equalTo;

import com.exercise.hotel.dao.ReservationRepository;
import com.exercise.hotel.dao.RoomRepository;
import com.exercise.hotel.dto.RatingDto;
import com.exercise.hotel.services.LinkService;
import com.exercise.hotel.services.ReservationService;

@WebMvcTest
public class RoomControllerTest {
	
	@Autowired
	private MockMvc mockMvc;	
	
	@MockBean
	private RoomRepository roomRepository;
	
	@MockBean
	private ReservationRepository reservationRepository;

	@MockBean
	private LinkService linkService;
	
	@MockBean
	private ReservationService reservationHandler;
	
	@MockBean
	private RestTemplate restTemplate;	
	
	@Test
	public void testGetRatingById() throws Exception {
		RatingDto dto = new RatingDto();
		dto.setRoomId(3L);
		dto.setRating("1");
		
		when(restTemplate.getForObject(anyString(), any())).thenReturn(dto);
		
		mockMvc.perform(get("/ratings/3")).andDo(print()).andExpect(status().
				isOk()).andExpect(content().string(equalTo("{\"roomId\":3,\"rating\":\"1\"}")));
	}
	
	@Test
	public void testGetRatings() throws Exception {
		RatingDto dto1 = new RatingDto();
		dto1.setRoomId(3L);
		dto1.setRating("1");
		RatingDto dto2 = new RatingDto();
		dto2.setRoomId(3L);
		dto2.setRating("1");
		RatingDto[] ratings = {dto1, dto2};
		
		when(restTemplate.getForEntity(anyString(), any())).thenReturn(new ResponseEntity(ratings, HttpStatus.OK));
		
		mockMvc.perform(get("/ratings")).andDo(print()).andExpect(status().
				isOk()).andExpect(content().string(equalTo("[{\"roomId\":3,\"rating\":\"1\"},{\"roomId\":3,\"rating\":\"1\"}]")));
	}
}