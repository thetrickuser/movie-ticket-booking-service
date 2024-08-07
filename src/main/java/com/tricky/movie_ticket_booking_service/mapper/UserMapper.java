package com.tricky.movie_ticket_booking_service.mapper;

import com.tricky.movie_ticket_booking_service.entity.User;
import com.tricky.movie_ticket_booking_service.model.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User mapDTOToEntity(UserDTO dto);
    UserDTO mapEntityToDTO(User entity);
}
