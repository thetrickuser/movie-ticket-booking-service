package com.tricky.movie_ticket_booking_service.service;

import com.tricky.movie_ticket_booking_service.entity.User;
import com.tricky.movie_ticket_booking_service.exception.UserAlreadyExistsException;
import com.tricky.movie_ticket_booking_service.exception.UserNotFoudException;
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

    public UserDTO addUser(UserDTO request) throws UserAlreadyExistsException, UserNotFoudException {
        String username = request.getEmail() == null ? request.getPhoneNumber() : request.getEmail();
        try {
            UserDTO existingUser = getUser(username);
            throw new UserAlreadyExistsException("User already exists");
        } catch (UserNotFoudException e) {
            User userEntity = userMapper.toEntity(request);
            User newUser = userRepository.save(userEntity);
            return userMapper.toDTO(newUser);
        }
    }

    public UserDTO getUser(String username) throws UserNotFoudException {
        Optional<User> userEntity = username.matches(".*[a-zA-Z].*") ? userRepository.findByEmail(username) : userRepository.findByPhoneNumber(username);
        return userEntity.map(user -> userMapper.toDTO(user)).orElseThrow(() -> new UserNotFoudException("User not found"));
    }

    public UserDTO updateUser(String username, UserDTO userDTO) throws UserNotFoudException {
        Optional<User> userEntity = username.matches(".*[a-zA-Z].*") ? userRepository.findByEmail(username) : userRepository.findByPhoneNumber(username);
        if (userEntity.isPresent()) {
            User existingUser = userEntity.get();
            User newUser = userMapper.toEntity(userDTO);
            newUser.setUser_id(existingUser.getUser_id());
            return userMapper.toDTO(userRepository.save(newUser));
        }
        throw new UserNotFoudException("User not found");
    }
}
