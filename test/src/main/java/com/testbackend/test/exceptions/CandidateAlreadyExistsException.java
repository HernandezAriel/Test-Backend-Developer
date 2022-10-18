package com.testbackend.test.exceptions;

public class CandidateAlreadyExistsException extends Exception {

    public CandidateAlreadyExistsException(String message){
        super(message);
    }
}
