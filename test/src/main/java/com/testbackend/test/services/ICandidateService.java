package com.testbackend.test.services;

import com.testbackend.test.exceptions.CandidateAlreadyExistsException;
import com.testbackend.test.exceptions.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.exceptions.CandidateNotExistsException;
import com.testbackend.test.exceptions.TechnologyNotExistsException;
import com.testbackend.test.models.dtos.CandidateDto;
import com.testbackend.test.models.entities.Candidate;
import java.util.List;

public interface ICandidateService {

    Candidate addCandidate(Candidate candidate) throws CandidateAlreadyExistsException;
    List<CandidateDto> getAllCandidates();
    Candidate getCandidateById(Long idCandidate) throws CandidateNotExistsException;
    CandidateDto getCandidateDtoById(Long idCandidate) throws CandidateNotExistsException;
    Candidate addTechnologyToCandidate(Long idCandidate, Long idTechnology, Long experience) throws CandidateNotExistsException, TechnologyNotExistsException, CandidateByTechnologyAlreadyExistsException;
    Candidate updateCandidate(Long candidateId, Candidate candidate) throws CandidateNotExistsException;
    void deleteCandidate(Long idCandidate) throws CandidateNotExistsException;

}
