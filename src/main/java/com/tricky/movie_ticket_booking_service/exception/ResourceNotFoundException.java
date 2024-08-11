package com.tricky.movie_ticket_booking_service.exception;

public class ResourceNotFoundException extends RuntimeException {

    private String message;

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
