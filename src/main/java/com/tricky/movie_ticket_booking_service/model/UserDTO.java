package com.tricky.movie_ticket_booking_service.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {

    private String name;

    private int id;

    private String email;
    private String phoneNumber;
    private transient String password;
    private String userType;
}
