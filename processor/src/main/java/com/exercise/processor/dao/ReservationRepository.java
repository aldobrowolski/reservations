package com.exercise.processor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.processor.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
