package com.tricky.movie_ticket_booking_service.service;

import com.tricky.movie_ticket_booking_service.entity.Movie;
import com.tricky.movie_ticket_booking_service.exception.ResourceNotFoundException;
import com.tricky.movie_ticket_booking_service.mapper.MovieMapper;
import com.tricky.movie_ticket_booking_service.model.MovieDTO;
import com.tricky.movie_ticket_booking_service.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll().stream().map(movieMapper::toDto).toList();
    }

    public MovieDTO getMovie(int movieId) throws ResourceNotFoundException {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isPresent()) return movieOptional.map(movieMapper::toDto).get();
        throw new ResourceNotFoundException("Movie not found");
    }

    public MovieDTO addMovie(MovieDTO request) {
        return movieMapper.toDto(movieRepository.save(movieMapper.toEntity(request)));
    }
}
