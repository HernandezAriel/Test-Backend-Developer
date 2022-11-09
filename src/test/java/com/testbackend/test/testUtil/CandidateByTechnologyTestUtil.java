package com.testbackend.test.testUtil;

import com.testbackend.test.model.dto.CandidateByTechnologyAddDto;
import com.testbackend.test.model.dto.ExperienceDto;
import com.testbackend.test.model.entity.CandidateByTechnology;
import com.testbackend.test.model.enums.DocumentType;
import com.testbackend.test.projection.CandidateByTechnologyProjection;

import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidate;
import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnology;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CandidateByTechnologyTestUtil {
    public static CandidateByTechnology getCandidateByTechnology() {
        return CandidateByTechnology.builder()
                .idCandidateByTechnology(1L)
                .candidate(getCandidate())
                .technology(getTechnology())
                .experience(1L)
                .build();
    }

    public static List<CandidateByTechnology> getListCandidateByTechnology() {
        return List.of(getCandidateByTechnology());
    }

    public static ExperienceDto getExperienceDto() {
        return ExperienceDto.builder()
                .name("Java")
                .version("11")
                .experience(1L)
                .build();
    }

    public static CandidateByTechnologyAddDto getCandidateByTechnologyAddDto() {
        return CandidateByTechnologyAddDto.builder()
                .candidateId(1L)
                .technologyId(1L)
                .experience(1L).build();
    }

    public static List<ExperienceDto> getListExperienceDto() {
        return List.of(getExperienceDto());
    }

    public static List<CandidateByTechnologyProjection> getListCandidateByTechnologyProjection() {
        var list = new ArrayList<CandidateByTechnologyProjection>();
        list.add(getCandidateByTechnologyProjection());
        return list;
    }

    public static CandidateByTechnologyProjection getCandidateByTechnologyProjection() {

        return new CandidateByTechnologyProjection() {

            @Override
            public String getName() {
                return "Ryu";
            }

            @Override
            public String getLastName() {
                return "Hoshi";
            }

            @Override
            public DocumentType getDniType() {
                return DocumentType.DNI;
            }

            @Override
            public String getDni() {
                return "987654321";
            }

            @Override
            public Date getBirthDate() {
                return new Date();
            }

            @Override
            public String getNameTechnology() {
                return "java";
            }

            @Override
            public String getExperience() {
                return "1";
            }
        };
    }
}