package com.testbackend.test.exception.handler;

import com.testbackend.test.exception.CandidateAlreadyExistsException;
import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.exception.TechnologyAlreadyExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CandidateAlreadyExistsException.class)
    ResponseEntity<ResponseMessage> candidateAlreadyExistsException(CandidateAlreadyExistsException candidateAlreadyExistsException){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body((ResponseMessage.builder()
                        .message(candidateAlreadyExistsException.getMessage()).build()));
    }

    @ExceptionHandler(CandidateByTechnologyAlreadyExistsException.class)
    ResponseEntity<ResponseMessage> candidateByTechnologyAlreadyExistsException(CandidateByTechnologyAlreadyExistsException candidateByTechnologyAlreadyExistsException){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body((ResponseMessage.builder()
                        .message(candidateByTechnologyAlreadyExistsException.getMessage()).build()));
    }

    @ExceptionHandler(CandidateNotExistsException.class)
    ResponseEntity<ResponseMessage> candidateNotExistsException(CandidateNotExistsException candidateNotExistsException ){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body((ResponseMessage.builder()
                        .message(candidateNotExistsException.getMessage()).build()));
    }

    @ExceptionHandler(TechnologyAlreadyExistsException.class)
    ResponseEntity<ResponseMessage> technologyAlreadyExistsException(TechnologyAlreadyExistsException technologyAlreadyExistsException ){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body((ResponseMessage.builder()
                        .message(technologyAlreadyExistsException.getMessage()).build()));
    }

    @ExceptionHandler(TechnologyNotExistsException.class)
    ResponseEntity<ResponseMessage> technologyNotExistsException(TechnologyNotExistsException technologyNotExistsException ){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body((ResponseMessage.builder()
                        .message(technologyNotExistsException.getMessage()).build()));
    }

}
