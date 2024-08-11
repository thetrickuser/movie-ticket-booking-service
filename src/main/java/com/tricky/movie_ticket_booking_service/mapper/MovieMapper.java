package com.tricky.movie_ticket_booking_service.mapper;

import com.tricky.movie_ticket_booking_service.entity.Movie;
import com.tricky.movie_ticket_booking_service.model.MovieDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDTO toDto(Movie entity);
    Movie toEntity(MovieDTO dto);

}
