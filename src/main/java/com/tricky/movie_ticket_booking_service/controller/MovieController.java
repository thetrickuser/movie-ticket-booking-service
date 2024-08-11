package com.tricky.movie_ticket_booking_service.controller;

import com.tricky.movie_ticket_booking_service.model.MovieDTO;
import com.tricky.movie_ticket_booking_service.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/")
    public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO request) {
        MovieDTO movieDTO = movieService.addMovie(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<MovieDTO>> getMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovies(@PathVariable int id) {
        return ResponseEntity.ok(movieService.getMovie(id));
    }
}
