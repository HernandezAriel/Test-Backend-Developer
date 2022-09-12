package com.testbackend.test.services.Imp;

import com.testbackend.test.models.dtos.CandidateDto;
import com.testbackend.test.models.entities.Candidate;
import com.testbackend.test.repositories.CandidateRepository;
import com.testbackend.test.services.ICandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            candidatesDto.add(convert(candidate, candidateByTechnologyService.getExperiencesByCandidate(candidate)));
        }
        return candidatesDto;
    }

}
