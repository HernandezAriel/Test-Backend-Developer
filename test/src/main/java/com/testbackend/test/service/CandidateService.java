package com.testbackend.test.service;

import com.testbackend.test.exception.CandidateAlreadyExistsException;
import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.entity.Candidate;
import java.util.List;

public interface CandidateService {

    Candidate addCandidate(Candidate candidate) throws CandidateAlreadyExistsException;
    List<CandidateDto> getAllCandidates();
    Candidate getCandidateById(Long idCandidate) throws CandidateNotExistsException;
    CandidateDto getCandidateDtoById(Long idCandidate) throws CandidateNotExistsException;
    Candidate addTechnologyToCandidate(Long idCandidate, Long idTechnology, Long experience) throws CandidateNotExistsException, TechnologyNotExistsException, CandidateByTechnologyAlreadyExistsException;
    Candidate updateCandidate(Candidate candidate) throws CandidateNotExistsException;
    void deleteCandidate(Long idCandidate) throws CandidateNotExistsException;

}
