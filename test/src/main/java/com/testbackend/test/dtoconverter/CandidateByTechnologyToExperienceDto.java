package com.testbackend.test.dtoconverter;

import com.testbackend.test.models.dtos.ExperienceDto;
import com.testbackend.test.models.entities.CandidateByTechnology;

public class CandidateByTechnologyToExperienceDto {

    public static ExperienceDto converter(CandidateByTechnology cbt){
        return ExperienceDto.builder()
                .name(cbt.getTechnology().getTechnology())
                .version(cbt.getTechnology().getVersion())
                .experience(cbt.getExperience())
                .build();
    }
}
