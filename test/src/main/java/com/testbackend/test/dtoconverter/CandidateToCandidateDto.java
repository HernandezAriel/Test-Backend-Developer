package com.testbackend.test.dtoconverter;

import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.dto.ExperienceDto;
import com.testbackend.test.model.entity.Candidate;

import java.util.List;

public class CandidateToCandidateDto {
    public static CandidateDto converter(Candidate candidate, List<ExperienceDto> technologies){
        return CandidateDto.builder()
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .documentType(candidate.getDocumentType())
                .documentNumber(candidate.getDocumentNumber())
                .birthDate(candidate.getBirthDate())
                .technologies(technologies)
                .build();
    }
}
