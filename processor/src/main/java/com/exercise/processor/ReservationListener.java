package com.exercise.processor;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.exercise.hotel.dto.ReservationDto;
import com.exercise.processor.services.ReservationService;

import lombok.AllArgsConstructor;

@EnableBinding(ReservationBinding.class)
@AllArgsConstructor
public class ReservationListener {

	private final ReservationService reservationService;
	
    @StreamListener(target = ReservationBinding.RESERVATION)
    public void processReservation(ReservationDto dto) {
        reservationService.updateReservation(dto);
    }	
}
