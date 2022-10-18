package com.testbackend.test.dtoconverter;

import com.testbackend.test.model.dto.ExperienceDto;
import com.testbackend.test.model.entity.CandidateByTechnology;

public class CandidateByTechnologyToExperienceDto {

    public static ExperienceDto converter(CandidateByTechnology cbt){
        return ExperienceDto.builder()
                .name(cbt.getTechnology().getName())
                .version(cbt.getTechnology().getVersion())
                .experience(cbt.getExperience())
                .build();
    }
}
