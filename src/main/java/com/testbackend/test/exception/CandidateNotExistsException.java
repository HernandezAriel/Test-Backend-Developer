package com.testbackend.test.exception;

public class CandidateNotExistsException extends RuntimeException {

    public CandidateNotExistsException(String message) {
        super(message);
    }
}
