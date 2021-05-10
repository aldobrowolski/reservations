package com.exercise.hotel.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import com.exercise.hotel.ReservationNotFoundException;
import com.exercise.hotel.dao.ReservationRepository;
import com.exercise.hotel.dao.RoomRepository;
import com.exercise.hotel.dto.ReservationDto;
import com.exercise.hotel.model.Reservation;
import com.exercise.hotel.model.Room;
import com.exercise.hotel.model.RoomType;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

	@Mock
	private ReservationRepository reservationRepository;

	@Mock
	private RoomRepository roomRepository;

	private ReservationService reservationService;

	@Before
	public void setUp() {
		reservationService = new ReservationService(reservationRepository, roomRepository);
	}

	@Test
	public void testFindAllReservations() {
		when(reservationRepository.findAll()).thenReturn(Collections.singletonList(createReservation()));
		List<ReservationDto> expectedReservations = Collections.singletonList(createReservationDto());

		List<ReservationDto> actualReservations = reservationService.findAll();

		assertEquals(expectedReservations, actualReservations);
	}

	@Test
	public void testFindReservationById() {
		when(reservationRepository.findById(any())).thenReturn(Optional.of(createReservation()));
		ReservationDto expectedReservation = createReservationDto();

		ReservationDto actualReservation = reservationService.getReservation(1L);

		assertEquals(expectedReservation, actualReservation);
	}

	@Test(expected = ReservationNotFoundException.class)
	public void testFindReservationByInvalidId() {
		when(reservationRepository.findById(any())).thenReturn(Optional.empty());

		reservationService.getReservation(1L);
	}
		
	@Test
	public void testFindReservationsByRoomId() {
		when(reservationRepository.findByRoomId(any())).thenReturn(Collections.singletonList(createReservation()));
		List<ReservationDto> expectedReservations = Collections.singletonList(createReservationDto());

		List<ReservationDto> actualReservations = reservationService.findByRoomId(1L);

		assertEquals(expectedReservations, actualReservations);
	}

	@Test
	public void testAddBasicReservation() {
		when(roomRepository.findRoom(any(), any(), any())).thenReturn(Collections.singletonList(new Room()));
		Long expectedId = 1L;
		when(reservationRepository.save(any())).thenReturn(Reservation.builder().id(expectedId).build());

		ReservationDto dto = createReservationDto();

		Long actualId = reservationService.addReservation(dto);
		assertEquals(expectedId, actualId);
	}

	@Test
	public void testAddSuiteReservation() {
		when(roomRepository.findRoom(any(), any(), any())).thenReturn(Collections.singletonList(new Room()));
		Long expectedId = 1L;
		when(reservationRepository.save(any())).thenReturn(Reservation.builder().id(expectedId).build());

		ReservationDto dto = createReservationDto();
		dto.setPeopleNumber(5);

		Long actualId = reservationService.addReservation(dto);
		assertEquals(expectedId, actualId);
	}

	@Test
	public void testAddPenthouseReservation() {
		when(roomRepository.findRoom(any(), any(), any())).thenReturn(Collections.singletonList(new Room()));
		Long expectedId = 1L;
		when(reservationRepository.save(any())).thenReturn(Reservation.builder().id(expectedId).build());

		ReservationDto dto = createReservationDto();
		dto.setPeopleNumber(7);

		Long actualId = reservationService.addReservation(dto);
		assertEquals(expectedId, actualId);
	}

	@Test(expected = ReservationNotFoundException.class)
	public void testAddTooManyPeople() {
		ReservationDto dto = createReservationDto();
		dto.setPeopleNumber(9);

		reservationService.addReservation(dto);
	}

	@Test(expected = ReservationNotFoundException.class)
	public void testMissingPeopleNumber() {
		ReservationDto dto = createReservationDto();
		dto.setPeopleNumber(null);

		reservationService.addReservation(dto);
	}
	
	@Test(expected = ReservationNotFoundException.class)
	public void testMissingRoomReservation() {
		when(roomRepository.findRoom(any(), any(), any())).thenReturn(Collections.emptyList());

		ReservationDto dto = createReservationDto();

		reservationService.addReservation(dto);
	}
	
	@Test
	public void testSaveReservation() {
		Reservation expectedReservation = createReservation();
		when(reservationRepository.save(any())).thenReturn(expectedReservation);
		when(reservationRepository.findById(any())).thenReturn(Optional.of(expectedReservation));
		
		ReservationDto dto = createReservationDto();
		
		Reservation actualReservation = reservationService.saveReservation(1L, dto);
		assertEquals(expectedReservation, actualReservation);
	}

	@Test
	public void testSaveReservationNoPeopleChange() {
		Reservation expectedReservation = createReservation();
		when(reservationRepository.save(any())).thenReturn(expectedReservation);
		when(reservationRepository.findById(any())).thenReturn(Optional.of(expectedReservation));
		
		ReservationDto dto = createReservationDto();
		dto.setPeopleNumber(null);
		
		Reservation actualReservation = reservationService.saveReservation(1L, dto);
		assertEquals(expectedReservation, actualReservation);
	}
		
	@Test(expected = ReservationNotFoundException.class)
	public void testSaveReservationNotAvailable() {
		Reservation expectedReservation = createReservation();
		when(reservationRepository.findById(any())).thenReturn(Optional.of(expectedReservation));
		when(reservationRepository.findConflictingReservations(any(), any(), any(), any()))
		.thenReturn(Collections.singletonList(new Reservation()));
		
		ReservationDto dto = createReservationDto();
		
		reservationService.saveReservation(1L, dto);
	}

	@Test(expected = ReservationNotFoundException.class)
	public void testSaveReservationTooManyPeople() {
		Reservation expectedReservation = createReservation();
		when(reservationRepository.findById(any())).thenReturn(Optional.of(expectedReservation));
		
		ReservationDto dto = createReservationDto();
		dto.setPeopleNumber(5);
		
		reservationService.saveReservation(1L, dto);
	}
	
	@Test
	public void testDeleteReservation() {
		when(reservationRepository.existsById(any())).thenReturn(true);
		
		reservationService.deleteReservation(1L);
		verify(reservationRepository).deleteById(any());
	}

	@Test
	public void testDeleteNonExistingReservation() {
		when(reservationRepository.existsById(any())).thenReturn(false);
		
		reservationService.deleteReservation(1L);
		verify(reservationRepository, times(0)).deleteById(any());
	}
		
	private Reservation createReservation() {
		return Reservation.builder().id(1L).userName("Bob").peopleNumber(3).startDate(LocalDate.of(2020, 7, 29))
				.endDate(LocalDate.of(2020, 7, 30)).room(Room.builder().id(1L).type(RoomType.BASIC).build()).build();
	}

	private ReservationDto createReservationDto() {
		return ReservationDto.builder().id(1L).userName("Bob").peopleNumber(3).startDate(LocalDate.of(2020, 7, 29))
				.endDate(LocalDate.of(2020, 7, 30)).roomId(1L).type(RoomType.BASIC).build();
	}
}
