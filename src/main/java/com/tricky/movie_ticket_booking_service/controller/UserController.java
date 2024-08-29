package com.tricky.movie_ticket_booking_service.controller;

import com.tricky.movie_ticket_booking_service.exception.DuplicateResourceException;
import com.tricky.movie_ticket_booking_service.exception.ResourceNotFoundException;
import com.tricky.movie_ticket_booking_service.model.UserDTO;
import com.tricky.movie_ticket_booking_service.service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) throws DuplicateResourceException {
        UserDTO response = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(value = "id") String username)
            throws ResourceNotFoundException {
        UserDTO user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") int userId, @RequestBody UserDTO userDTO)
            throws ResourceNotFoundException {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) throws ResourceNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO request) {
        UserDTO response = userService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid UserDTO request) {
        UserDTO response = userService.login(request);
        return ResponseEntity.ok().body(response);
    }

}
