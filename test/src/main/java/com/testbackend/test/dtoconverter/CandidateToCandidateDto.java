package com.testbackend.test.dtoconverter;

import com.testbackend.test.models.dtos.CandidateDto;
import com.testbackend.test.models.dtos.ExperienceDto;
import com.testbackend.test.models.entities.Candidate;

import java.util.List;

public class CandidateToCandidateDto {
    public static CandidateDto converter(Candidate candidate, List<ExperienceDto> technologies){
        return CandidateDto.builder()
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .documentType(candidate.getDocumentType())
                .document(candidate.getDocumentNumber())
                .birthdate(candidate.getBirthdate())
                .technologies(technologies)
                .build();
    }
}
