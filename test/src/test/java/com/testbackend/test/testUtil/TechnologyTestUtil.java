package com.testbackend.test.testUtil;

import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.model.entity.Technology;

import java.util.List;

public class TechnologyTestUtil {

    public static Technology getTechnology() {
        return Technology.builder()
                .idTechnology(1L)
                .name("Java")
                .version("11")
                .build();
    }

    public static TechnologyDto getTechnologyDto(){
        return TechnologyDto.builder()
                .name("Java")
                .version("11")
                .build();
    }

    public static List<TechnologyDto> getListTechnologyDto() {
        return List.of(getTechnologyDto());
    }

    public static List<Technology> getListTechnology() {
        return List.of(getTechnology());
    }
}
