package com.tricky.movie_ticket_booking_service.service;

import com.tricky.movie_ticket_booking_service.entity.User;
import com.tricky.movie_ticket_booking_service.exception.DuplicateResourceException;
import com.tricky.movie_ticket_booking_service.exception.ResourceNotFoundException;
import com.tricky.movie_ticket_booking_service.mapper.UserMapper;
import com.tricky.movie_ticket_booking_service.model.UserDTO;
import com.tricky.movie_ticket_booking_service.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
    }

    public UserDTO addUser(UserDTO request) throws DuplicateResourceException {
        String email = request.getEmail();
        String phoneNumber = request.getPhoneNumber();

        if ((!StringUtils.isBlank(email) && userRepository.existsByEmail(email)) ||
                (!StringUtils.isBlank(phoneNumber) && userRepository.existsByPhoneNumber(phoneNumber)))
            throw new DuplicateResourceException("User already exists");

        User userEntity = userMapper.toEntity(request);
        User newUser = userRepository.save(userEntity);
        return userMapper.toDTO(newUser);
    }

    public UserDTO getUser(String username) throws ResourceNotFoundException {
        Optional<User> userEntity = username.matches(".*[a-zA-Z].*") ? userRepository.findByEmail(username) : userRepository.findByPhoneNumber(username);
        return userEntity.map(userMapper::toDTO).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserDTO updateUser(int userId, UserDTO userDTO) throws ResourceNotFoundException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        User newUser = userMapper.toEntity(userDTO);
        newUser.setId(existingUser.getId());
        return userMapper.toDTO(userRepository.save(newUser));
    }

    public void deleteUser(int id) throws ResourceNotFoundException {
        if (!userRepository.existsById(id)) throw new ResourceNotFoundException("User does not exist");
        userRepository.deleteById(id);
    }

    public UserDTO login(UserDTO request) {
        User principal = (User) authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())).getPrincipal();
        return userMapper.toDTO(principal);
    }
}
