package com.tricky.movie_ticket_booking_service.repository;

import com.tricky.movie_ticket_booking_service.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
