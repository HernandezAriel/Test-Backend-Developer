package com.testbackend.test.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class UrlBuilder {

    public static <T> URI buildURL(String entity, T id){
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("{entity}/{id}")
                .buildAndExpand(entity, id)
                .toUri();
    }
}
