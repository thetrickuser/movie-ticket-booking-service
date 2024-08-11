package com.tricky.movie_ticket_booking_service.repository;

import com.tricky.movie_ticket_booking_service.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
}
