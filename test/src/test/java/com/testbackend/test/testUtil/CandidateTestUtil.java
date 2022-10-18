package com.testbackend.test.testUtil;

import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.enums.DocumentType;

import java.util.List;


public class CandidateTestUtil {

    public static Candidate getCandidate() {
        return Candidate.builder()
                .idCandidate(1L)
                .firstName("Ryu")
                .lastName("Hoshi")
                .documentType(DocumentType.DNI)
                .documentNumber("987654321")
                .birthdate(null)
                .build();
    }

    public static CandidateDto getCandidateDto() {
        return CandidateDto.builder()
                .idCandidate(1L)
                .firstName("Ryu")
                .lastName("Hoshi")
                .documentType(DocumentType.DNI)
                .documentNumber("987654321")
                .birthdate(null)
                .build();
    }

    public static List<CandidateDto> getListCandidateDto(){
        return List.of(getCandidateDto());
    }

}
