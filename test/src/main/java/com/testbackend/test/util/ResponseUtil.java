package com.testbackend.test.util;

import com.testbackend.test.model.util.ResponseMessage;

public class ResponseUtil {

    public static ResponseMessage messageResponse(String message){
        return ResponseMessage.builder().message(message).build();
    }
}
