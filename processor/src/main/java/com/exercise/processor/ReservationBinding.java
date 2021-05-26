package com.exercise.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ReservationBinding {

    String RESERVATION = "reservationChannel";

    @Input(RESERVATION)
    SubscribableChannel reservations();
}
