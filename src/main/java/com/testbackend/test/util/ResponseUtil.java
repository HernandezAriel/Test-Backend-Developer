package com.testbackend.test.util;

import com.testbackend.test.model.util.ResponseMessage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseUtil {
    public static ResponseMessage messageResponse(String message){
        return ResponseMessage.builder().message(message).build();
    }
}
