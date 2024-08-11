package com.tricky.movie_ticket_booking_service.exception;

public class DuplicateResourceException extends RuntimeException {
    String message;

    public DuplicateResourceException(String message) {
        super(message);
        this.message = message;
    }
}
