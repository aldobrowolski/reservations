package com.exercise.ratings;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.equalTo;

import com.exercise.ratings.dao.RatingRepository;
import com.exercise.ratings.model.Rating;

@WebMvcTest
public class RatingControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RatingRepository ratingRepository;
	
	@Test	
	public void testGetRatingById() throws Exception {
		Rating rating = new Rating();
		rating.setId(3L);
		rating.setRating("1");
		
		when(ratingRepository.findById(anyLong())).thenReturn(Optional.of(rating));
		
		mockMvc.perform(get("/ratings/3")).andDo(print()).andExpect(status().
				isOk()).andExpect(content().string(equalTo("{\"roomId\":3,\"rating\":\"1\"}")));
	}
	
	@Test	
	public void testGetAllRatings() throws Exception {
		Rating rating = new Rating();
		rating.setId(4L);
		rating.setRating("2");
		List<Rating> ratings = new ArrayList<>();
		ratings.add(rating);
		
		when(ratingRepository.findAll()).thenReturn(ratings);
		
		mockMvc.perform(get("/ratings")).andDo(print()).andExpect(status().
				isOk()).andExpect(content().string(equalTo("[{\"roomId\":4,\"rating\":\"2\"}]")));
	}
	
}
