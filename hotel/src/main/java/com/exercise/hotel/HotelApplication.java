package com.exercise.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.handler.annotation.SendTo;

import com.exercise.hotel.dto.ReservationDto;

@EnableBinding(ReservationBinding.class)
@EnableDiscoveryClient
@SpringBootApplication
public class HotelApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}	
}
