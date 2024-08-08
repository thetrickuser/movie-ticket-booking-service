package com.tricky.movie_ticket_booking_service.exception;

public class UserAlreadyExistsException extends Exception {
    String message;

    public UserAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
