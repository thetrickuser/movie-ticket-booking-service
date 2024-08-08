package com.tricky.movie_ticket_booking_service.mapper;

import com.tricky.movie_ticket_booking_service.entity.User;
import com.tricky.movie_ticket_booking_service.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserMapper {

    User toEntity(UserDTO dto);
    UserDTO toDTO(User entity);

    @Mapping(source = "")
    User existingUserToEntity(UserDTO newUser, @MappingTarget User userEntity);
}
