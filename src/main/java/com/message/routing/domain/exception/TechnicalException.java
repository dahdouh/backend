package com.message.routing.domain.exception;

public class TechnicalException extends RuntimeException {
    private static final String MESSAGE = "Technical error has been occurred";

    public TechnicalException(final Throwable cause) {
        super(MESSAGE, cause);
    }
}
