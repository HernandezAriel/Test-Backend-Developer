package com.testbackend.test.dtoconverter;

import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.dto.ExperienceDto;
import com.testbackend.test.model.entity.Candidate;

import java.util.List;

public class CandidateMapper {
    public static CandidateDto converterCandidateToDtoExp(Candidate candidate, List<ExperienceDto> technologies){
        return CandidateDto.builder()
                .idCandidate(candidate.getIdCandidate())
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .documentType(candidate.getDocumentType())
                .documentNumber(candidate.getDocumentNumber())
                .birthDate(candidate.getBirthDate())
                .build();
    }

    public static CandidateDto converterCandidateToDto(Candidate candidate){
        return CandidateDto.builder()
                .idCandidate(candidate.getIdCandidate())
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .documentType(candidate.getDocumentType())
                .documentNumber(candidate.getDocumentNumber())
                .birthDate(candidate.getBirthDate())
                .build();
    }

    public static Candidate converterDtoToCandidate(CandidateDto candidateDto) {
        return Candidate.builder()
                .idCandidate(candidateDto.getIdCandidate())
                .firstName(candidateDto.getFirstName())
                .lastName(candidateDto.getLastName())
                .documentType(candidateDto.getDocumentType())
                .documentNumber(candidateDto.getDocumentNumber())
                .birthDate(candidateDto.getBirthDate()).build();
    }
}
}
