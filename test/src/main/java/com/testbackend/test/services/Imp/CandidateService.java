package com.testbackend.test.services.Imp;

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

    public Candidate addCandidate(Candidate candidate){
        return candidateRepository.save(candidate);
    }

    public List<CandidateDto> getAllCandidates(){
        List<CandidateDto> candidatesDto = new ArrayList<>();
        for(Candidate candidate : candidateRepository.findAll()){
            candidatesDto.add(converter(candidate, candidateByTechnologyService.getExperiencesByCandidate(candidate)));
        }
        return candidatesDto;
    }
    public Candidate getCandidateById(Long idCandidate){
        return candidateRepository.findById(idCandidate).orElse(null);
    }

    public CandidateDto getCandidateDtoById(Long idCandidate){
        Candidate candidate = getCandidateById(idCandidate);
        return converter(candidate, candidateByTechnologyService.getExperiencesByCandidate(candidate));
    }

    public Candidate addTechnologyToCandidate(Long idCandidate, Long idTechnology, int yearsExperience) {
        Candidate candidate = getCandidateById(idCandidate);
        Technology technology = technologyService.getTechnologyById(idTechnology);
        candidateByTechnologyService.addCandidateByTechnology(candidate, technology, yearsExperience);
        return candidate;
    }

    public Candidate updateCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public void deleteCandidate(Long idCandidate) {
        Candidate candidate = getCandidateById(idCandidate);
        if(candidateByTechnologyService.getCandidatesByTechnologyByCandidate(candidate).isEmpty())
            candidateRepository.deleteById(idCandidate);
    }
}
