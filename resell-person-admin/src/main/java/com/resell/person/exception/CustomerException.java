package com.resell.person.exception;

public class CustomerException extends Exception {
    public CustomerException(String message) {
        super(message);
    }

    public CustomerException(String message, Exception cause) {
        super(message, cause);
    }

    public CustomerException(Exception cause) {
        super(cause);
    }
}
