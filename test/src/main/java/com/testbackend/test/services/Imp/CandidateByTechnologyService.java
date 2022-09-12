package com.testbackend.test.services.Imp;

import org.modelmapper.ModelMapper;
import com.testbackend.test.models.dtos.ExperienceDto;
import com.testbackend.test.models.entities.Candidate;
import com.testbackend.test.models.entities.CandidateByTechnology;
import com.testbackend.test.models.entities.Technology;
import com.testbackend.test.repositories.CandidateByTechnologyRepository;
import com.testbackend.test.services.ICandidateByTechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.testbackend.test.dtoconverter.CandidateByTechnologyToExperienceDto.converter;

@Service
public class CandidateByTechnologyService implements ICandidateByTechnologyService {

    @Autowired
    CandidateByTechnologyRepository candidateByTechnologyRepository;
    @Autowired
    ModelMapper modelMapper;

    public void addCandidateByTechnology(Candidate candidate, Technology technology, int experience){
        if((candidateByTechnologyRepository.findByCandidateAndTechnology(candidate, technology)) == null)
            candidateByTechnologyRepository.save(CandidateByTechnology.builder()
                    .candidate(candidate)
                    .technology(technology)
                    .experience(experience)
                    .build());
    }

    public List<CandidateByTechnology> getCandidatesByTechnologyByCandidate(Candidate candidate){
        return candidateByTechnologyRepository.findByCandidate(candidate);
    }

    public List<ExperienceDto> getExperiencesByCandidate(Candidate candidate) {
        List<ExperienceDto> experiences = new ArrayList<>();
        for(CandidateByTechnology cbt : candidateByTechnologyRepository.findByCandidate(candidate)) {
            experiences.add(converter(cbt));
        }
        return experiences;
    }
    public List<CandidateByTechnology> getCandidatesByTechnologyByTechnology(Technology technology) {
        return candidateByTechnologyRepository.findByTechnology(technology);
    }

}
