package com.exercise.hotel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ReservationBinding {

    @Output("reservationChannel")
    MessageChannel reservations();
}
