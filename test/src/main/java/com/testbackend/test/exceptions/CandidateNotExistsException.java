package com.testbackend.test.exceptions;

public class CandidateNotExistsException extends Exception{

    public CandidateNotExistsException(String message){
        super(message);
    }
}
