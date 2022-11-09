package com.testbackend.test.testUtil;

import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.enums.DocumentType;

import java.util.ArrayList;
import java.util.List;

public class CandidateTestUtil {

    public static Candidate getCandidate() {
        return Candidate.builder()
                .idCandidate(1L)
                .firstName("Ryu")
                .lastName("Hoshi")
                .documentType(DocumentType.DNI)
                .documentNumber("987654321")
                .birthDate(null)
                .build();
    }

    public static CandidateDto getCandidateDto() {
        return CandidateDto.builder()
                .idCandidate(1L)
                .firstName("Ryu")
                .lastName("Hoshi")
                .documentType(DocumentType.DNI)
                .documentNumber("987654321")
                .birthDate(null)
                .build();
    }

    public static List<Candidate> getListCandidates() {
        var candidates = new ArrayList<Candidate>();
        candidates.add(getCandidate());
        return candidates;
    }

    public static List<CandidateDto> getListCandidateDto() {
        var candidates = new ArrayList<CandidateDto>();
        candidates.add(getCandidateDto());
        return candidates;
    }

}
