package com.tricky.movie_ticket_booking_service.service;

import com.tricky.movie_ticket_booking_service.entity.User;
import com.tricky.movie_ticket_booking_service.mapper.UserMapper;
import com.tricky.movie_ticket_booking_service.model.UserDTO;
import com.tricky.movie_ticket_booking_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO addUser(UserDTO request) {
        User userEntity = userMapper.mapDTOToEntity(request);
        User newUser = userRepository.save(userEntity);
        return userMapper.mapEntityToDTO(newUser);
    }

    public UserDTO getUser(String username) {
        Optional<User> userEntity = username.matches(".*[a-zA-Z].*") ? userRepository.findByEmail(username) : userRepository.findByPhoneNumber(username);
        if (userEntity.isPresent()) {
            return userMapper.mapEntityToDTO(userEntity.get());
        }
        return null;
    }
}
