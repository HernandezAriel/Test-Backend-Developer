package com.testbackend.test.services;

import com.testbackend.test.models.dtos.CandidateDto;
import com.testbackend.test.models.entities.Candidate;
import java.util.List;

public interface ICandidateService {

    Candidate addCandidate(Candidate candidate);
    List<CandidateDto> getAllCandidates();
    Candidate getCandidateById(Long idCandidate);
    CandidateDto getCandidateDtoById(Long idCandidate);
    Candidate addTechnologyToCandidate(Long idCandidate, Long idTechnology, int experience);
    Candidate updateCandidate(Candidate candidate);
    void deleteCandidate(Long idCandidate);

}
