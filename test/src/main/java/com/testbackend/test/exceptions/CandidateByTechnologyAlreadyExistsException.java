package com.testbackend.test.exceptions;

public class CandidateByTechnologyAlreadyExistsException extends Exception{

    public CandidateByTechnologyAlreadyExistsException(String message){
        super(message);
    }
}
