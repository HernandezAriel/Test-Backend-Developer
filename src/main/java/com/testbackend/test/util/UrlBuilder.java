package com.testbackend.test.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlBuilder {

    public static <T> URI buildURL(String entity, T id) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("{entity}/{id}")
                .buildAndExpand(entity, id)
                .toUri();
    }
}
