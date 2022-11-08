package com.testbackend.test.service;

import com.testbackend.test.exception.CandidateAlreadyExistsException;
import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.entity.Candidate;
import java.util.List;
import java.util.Set;

public interface CandidateService {

    CandidateDto addCandidate(CandidateDto candidateDto);
    List<CandidateDto> getAllCandidates();
    Candidate getCandidateById(Long idCandidate);
    CandidateDto getCandidateDtoById(Long idCandidate);
    CandidateDto addTechnologyToCandidate(Long idCandidate, Long idTechnology, Long experience);
    void updateCandidate(CandidateDto candidateDto, Long id);
    void deleteCandidate(Long idCandidate);

}
