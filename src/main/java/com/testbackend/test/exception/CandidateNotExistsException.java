package com.testbackend.test.exception;

public class CandidateNotExistsException extends Exception{

    public CandidateNotExistsException(String message){
        super(message);
    }
}
