package com.tricky.movie_ticket_booking_service.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {

    @NotNull
    private String name;

    @NotNull
    private String email;
    private String phoneNumber;
    private String password;
}
