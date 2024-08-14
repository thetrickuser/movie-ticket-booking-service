package com.tricky.movie_ticket_booking_service.mapper;

import com.tricky.movie_ticket_booking_service.entity.User;
import com.tricky.movie_ticket_booking_service.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class UserMapper {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    public abstract User toEntity(UserDTO dto);

    @Mapping(target = "password", ignore = true)
    public abstract UserDTO toDTO(User entity);
}
