package com.tricky.movie_ticket_booking_service.controller;

import com.tricky.movie_ticket_booking_service.exception.UserAlreadyExistsException;
import com.tricky.movie_ticket_booking_service.exception.UserNotFoudException;
import com.tricky.movie_ticket_booking_service.model.UserDTO;
import com.tricky.movie_ticket_booking_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) throws UserAlreadyExistsException, UserNotFoudException {
        UserDTO response = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable(value = "id") String username) throws UserNotFoudException {
        return userService.getUser(username);
    }

    /*
     TODO add update user endpoint
     TODO add delete user endpoint
    */

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable(value = "id") String username, @RequestBody UserDTO userDTO) throws UserNotFoudException {
        return userService.updateUser(username, userDTO);
    }

}
