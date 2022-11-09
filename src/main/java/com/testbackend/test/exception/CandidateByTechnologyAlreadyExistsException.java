package com.testbackend.test.exception;

public class CandidateByTechnologyAlreadyExistsException extends RuntimeException {

    public CandidateByTechnologyAlreadyExistsException(String message) {
        super(message);
    }
}
