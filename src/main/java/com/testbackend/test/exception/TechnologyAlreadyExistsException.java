package com.testbackend.test.exception;

public class TechnologyAlreadyExistsException extends RuntimeException {

    public TechnologyAlreadyExistsException(String message) {
        super(message);
    }
}
