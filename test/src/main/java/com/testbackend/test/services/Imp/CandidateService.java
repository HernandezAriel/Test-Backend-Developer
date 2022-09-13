package com.testbackend.test.services.Imp;

import com.testbackend.test.exceptions.CandidateAlreadyExistsException;
import com.testbackend.test.exceptions.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.exceptions.CandidateNotExistsException;
import com.testbackend.test.exceptions.TechnologyNotExistsException;
import com.testbackend.test.models.dtos.CandidateDto;
import com.testbackend.test.models.entities.Candidate;
import com.testbackend.test.models.entities.Technology;
import com.testbackend.test.repositories.CandidateRepository;
import com.testbackend.test.services.ICandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.testbackend.test.dtoconverter.CandidateToCandidateDto.converter;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateService implements ICandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private TechnologyService technologyService;
    @Autowired
    private CandidateByTechnologyService candidateByTechnologyService;

    public Candidate addCandidate(Candidate candidate) throws CandidateAlreadyExistsException {
        if((candidateRepository.findByDocumentNumber(candidate.getDocumentNumber()))!= null) throw new CandidateAlreadyExistsException("Candidate already exists");
        else{
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
        return converter(candidate, candidateByTechnologyService.getExperiencesByCandidate(candidate));
    }


    public Candidate addTechnologyToCandidate(Long idCandidate, Long idTechnology, Long experience) throws CandidateNotExistsException, TechnologyNotExistsException, CandidateByTechnologyAlreadyExistsException {
        Candidate candidate = getCandidateById(idCandidate);
        Technology technology = technologyService.getTechnologyById(idTechnology);
        candidateByTechnologyService.addCandidateByTechnology(candidate, technology, experience);
        return candidate;
    }

    public Candidate updateCandidate(Long candidateId ,Candidate candidate) throws CandidateNotExistsException {
        if(candidateRepository.findById(candidateId) == null)
            throw new CandidateNotExistsException("Candidate not exists");
        else{
            return candidateRepository.save(candidate);
        }

    }

    public void deleteCandidate(Long idCandidate) throws CandidateNotExistsException  {
        Candidate candidate = getCandidateById(idCandidate);
        if(!candidateByTechnologyService.getCandidatesByTechnologyByCandidate(candidate).isEmpty())
            throw new CandidateNotExistsException("Candidate not exists");
        else{
            candidateRepository.deleteById(idCandidate);
        }

    }
}
