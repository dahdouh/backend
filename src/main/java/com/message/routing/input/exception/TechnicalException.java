package com.message.routing.input.exception;

public class TechnicalException extends RuntimeException {
    private static final String MESSAGE = "Technical error has been occured";

    public TechnicalException(final Throwable cause) {
        super(MESSAGE, cause);
    }
}
