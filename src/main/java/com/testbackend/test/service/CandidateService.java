package com.testbackend.test.service;

import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.entity.Candidate;

import java.util.List;

public interface CandidateService {

    CandidateDto addCandidate(CandidateDto candidateDto);

    List<CandidateDto> getAllCandidates();

    Candidate getCandidateById(Long idCandidate);

    CandidateDto getCandidateDtoById(Long idCandidate);

    void updateCandidate(CandidateDto candidateDto, Long id);

    void deleteCandidate(Long idCandidate);

}
