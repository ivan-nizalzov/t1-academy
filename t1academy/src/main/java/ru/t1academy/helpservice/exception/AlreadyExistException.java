package ru.t1academy.helpservice.exception;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(final String message) {
        super(message);
    }
}
