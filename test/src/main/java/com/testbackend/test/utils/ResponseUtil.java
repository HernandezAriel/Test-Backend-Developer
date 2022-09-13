package com.testbackend.test.utils;

import com.testbackend.test.models.utils.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseUtil {

    public static ResponseMessage messageResponse(String message){
        return ResponseMessage.builder().message(message).build();
    }
}
