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

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) throws UserAlreadyExistsException {
        UserDTO response = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(value = "id") String username) throws UserNotFoudException {
        UserDTO user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") int userId, @RequestBody UserDTO userDTO) throws UserNotFoudException {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) throws UserNotFoudException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
