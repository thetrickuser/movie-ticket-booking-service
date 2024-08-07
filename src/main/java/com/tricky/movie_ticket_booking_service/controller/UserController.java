package com.tricky.movie_ticket_booking_service.controller;

import com.tricky.movie_ticket_booking_service.model.UserDTO;
import com.tricky.movie_ticket_booking_service.service.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public UserDTO addUser(@RequestBody UserDTO user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable(value = "id", required = true) String username) {
        return userService.getUser(username);
    }

    /*
     TODO add update user endpoint
     TODO add delete user endpoint
    */

}
