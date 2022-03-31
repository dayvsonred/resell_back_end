package com.resell.person.exception;

public class PersonException extends Exception {
    public PersonException(String message) {
        super(message);
    }

    public PersonException(String message, Exception cause) {
        super(message, cause);
    }

    public PersonException(Exception cause) {
        super(cause);
    }
}
