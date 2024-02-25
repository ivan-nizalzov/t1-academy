package ru.t1academy.exception;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(final String message) {
        super(message);
    }
}
