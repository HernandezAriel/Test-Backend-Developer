package com.testbackend.test.exception;

public class CandidateByTechnologyAlreadyExistsException extends Exception{

    public CandidateByTechnologyAlreadyExistsException(String message){
        super(message);
    }
}
