package com.testbackend.test.dtoconverter;

import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.model.entity.Technology;

public class TechnologyMapper {

    public static TechnologyDto converterTechnologyToDto(Technology technology) {
        return TechnologyDto.builder()
                .id(technology.getIdTechnology())
                .name(technology.getName())
                .version(technology.getVersion())
                .build();
    }

    public static Technology converterDtoToTechnology(TechnologyDto technologyDto) {
        return Technology.builder()
                .idTechnology(technologyDto.getId())
                .name(technologyDto.getName())
                .version(technologyDto.getVersion())
                .build();
    }
}