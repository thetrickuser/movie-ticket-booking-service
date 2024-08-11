package com.tricky.movie_ticket_booking_service.mapper;

import com.tricky.movie_ticket_booking_service.entity.User;
import com.tricky.movie_ticket_booking_service.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    User toEntity(UserDTO dto);

    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User entity);
}
