package com.testbackend.test.models.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> errors;
}

