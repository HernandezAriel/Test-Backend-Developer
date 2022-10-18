package com.testbackend.test.service.Imp;

import com.testbackend.test.exception.CandidateAlreadyExistsException;
import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.repository.CandidateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.testbackend.test.dtoconverter.CandidateToCandidateDto.converter;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CandidateService implements com.testbackend.test.service.CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private TechnologyService technologyService;
    @Autowired
    private CandidateByTechnologyService candidateByTechnologyService;

    public Candidate addCandidate(Candidate candidate) throws CandidateAlreadyExistsException {
        if((candidateRepository.findByDocumentNumber(candidate.getDocumentNumber()))!= null){
            log.error("Candidate already exists");
            throw new CandidateAlreadyExistsException("Candidate already exists");
        }
        else{
            log.debug("Candidate to save: " + candidate);
            log.info("Candidate save");
            return candidateRepository.save(candidate);
        }
    }

    public List<CandidateDto> getAllCandidates(){
        List<CandidateDto> candidatesDto = new ArrayList<>();
        for(Candidate candidate : candidateRepository.findAll()){
            candidatesDto.add(converter(candidate, candidateByTechnologyService.getExperiencesByCandidate(candidate)));
        }
        return candidatesDto;
    }
    public Candidate getCandidateById(Long idCandidate) throws CandidateNotExistsException {
        return candidateRepository.findById(idCandidate).orElseThrow (() -> new CandidateNotExistsException("Candidate Not Exists"));
    }

    public CandidateDto getCandidateDtoById(Long idCandidate) throws CandidateNotExistsException{
        Candidate candidate = getCandidateById(idCandidate);
        log.debug("Candidate to Add Technology: " + candidate);
        return converter(candidate, candidateByTechnologyService.getExperiencesByCandidate(candidate));
    }


    public Candidate addTechnologyToCandidate(Long idCandidate, Long idTechnology, Long experience) throws CandidateNotExistsException, TechnologyNotExistsException, CandidateByTechnologyAlreadyExistsException {
        Candidate candidate = getCandidateById(idCandidate);
        Technology technology = technologyService.getTechnologyById(idTechnology);
        candidateByTechnologyService.addCandidateByTechnology(candidate, technology, experience);
        return candidate;
    }

    public Candidate updateCandidate(Candidate candidate) throws CandidateNotExistsException {
        if(candidate.getIdCandidate() == null || getCandidateById(candidate.getIdCandidate()) == null){
            log.error("Candidate not exists");
            throw new CandidateNotExistsException("Candidate not exists");
        }
        else{
            log.info("Candidate updated");
            return candidateRepository.save(candidate);
        }

    }

    public void deleteCandidate(Long idCandidate) throws CandidateNotExistsException  {
        Candidate candidate = getCandidateById(idCandidate);
        if(!candidateByTechnologyService.getCandidatesByTechnologyByCandidate(candidate).isEmpty()){
            log.error("Candidate not exists");
            throw new CandidateNotExistsException("Candidate not exists");
        }

        else{
            log.info("Candidate deleted");
            candidateRepository.deleteById(idCandidate);
        }

    }
}
