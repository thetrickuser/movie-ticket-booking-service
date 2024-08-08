package com.tricky.movie_ticket_booking_service.exception;

import java.util.function.Supplier;

public class UserNotFoudException extends Exception {

    private String message;

    public UserNotFoudException(String message) {
        super(message);
        this.message = message;
    }
}
