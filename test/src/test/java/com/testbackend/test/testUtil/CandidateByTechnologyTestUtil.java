package com.testbackend.test.testUtil;

import com.testbackend.test.model.dto.ExperienceDto;
import com.testbackend.test.model.entity.CandidateByTechnology;
import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidate;
import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnology;

import java.util.List;

public class CandidateByTechnologyTestUtil {
    public static CandidateByTechnology getCandidateForTechnology() {
        return CandidateByTechnology.builder()
                .idCandidateByTechnology(1L)
                .candidate(getCandidate())
                .technology(getTechnology())
                .experience(1L)
                .build();
    }

    public static List<CandidateByTechnology> getListCandidateForTechnology() {
        return List.of(getCandidateForTechnology());
    }

    public static ExperienceDto getExperienceDto() {
        return ExperienceDto.builder()
                .name("Java")
                .version("11")
                .experience(1L)
                .build();
    }

    public static List<ExperienceDto> getListExperienceDto() {
        return List.of(getExperienceDto());
    }
}
