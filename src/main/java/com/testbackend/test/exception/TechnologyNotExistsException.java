package com.testbackend.test.exception;

public class TechnologyNotExistsException extends RuntimeException {

    public TechnologyNotExistsException(String message) {
        super(message);
    }
}
