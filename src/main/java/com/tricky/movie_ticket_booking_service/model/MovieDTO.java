package com.tricky.movie_ticket_booking_service.model;

import com.tricky.movie_ticket_booking_service.entity.Genre;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MovieDTO {

    @NotNull
    private String title;

    private int id;

    @Size(min = 1)
    private List<Genre> genres;

    @NotNull
    private LocalDate releaseDate;
    private int duration;
}
