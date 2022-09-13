package com.testbackend.test.services.Imp;

import com.testbackend.test.exceptions.CandidateByTechnologyAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import com.testbackend.test.models.dtos.ExperienceDto;
import com.testbackend.test.models.entities.Candidate;
import com.testbackend.test.models.entities.CandidateByTechnology;
import com.testbackend.test.models.entities.Technology;
import com.testbackend.test.repositories.CandidateByTechnologyRepository;
import com.testbackend.test.services.ICandidateByTechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.testbackend.test.dtoconverter.CandidateByTechnologyToExperienceDto.converter;

@Slf4j
@Service
public class CandidateByTechnologyService implements ICandidateByTechnologyService {

    @Autowired
    CandidateByTechnologyRepository candidateByTechnologyRepository;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    public void addCandidateByTechnology(Candidate candidate, Technology technology, Long experience) throws CandidateByTechnologyAlreadyExistsException {
        CandidateByTechnology cbt = candidateByTechnologyRepository.findByCandidateAndTechnology(candidate, technology);
        if(cbt != null)

            throw new CandidateByTechnologyAlreadyExistsException("Thechnology " + technology.getName() + " already exists for this candidate");
        else {
            log.info("Technology added to candidate");
            candidateByTechnologyRepository.save(CandidateByTechnology.builder()
                    .candidate(candidate)
                    .technology(technology)
                    .experience(experience)
                    .build());
        }
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
