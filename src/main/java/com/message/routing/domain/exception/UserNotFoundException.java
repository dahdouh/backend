package com.message.routing.domain.exception;

public class UserNotFoundException extends RuntimeException {
    private static final String MESSAGE = "User %s is not found";

    public UserNotFoundException(final String username) {
        super(String.format(MESSAGE, username));
    }
}
