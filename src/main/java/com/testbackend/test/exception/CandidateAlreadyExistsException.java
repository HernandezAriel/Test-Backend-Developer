package com.testbackend.test.exception;

public class CandidateAlreadyExistsException extends Exception {

    public CandidateAlreadyExistsException(String message){
        super(message);
    }
}
