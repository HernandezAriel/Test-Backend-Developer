package com.testbackend.test.service.imp;

import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.exception.EmptyException;
import com.testbackend.test.model.dto.CandidateByTechnologyDto;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.projection.CandidateByTechnologyProjection;
import com.testbackend.test.service.CandidateByTechnologyService;
import com.testbackend.test.service.CandidateService;
import com.testbackend.test.service.TechnologyService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import com.testbackend.test.model.entity.CandidateByTechnology;
import com.testbackend.test.repository.CandidateByTechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class CandidateByTechnologyServiceImp implements CandidateByTechnologyService {

    private final CandidateByTechnologyRepository candidateByTechnologyRepository;


    @Autowired
    public CandidateByTechnologyServiceImp(CandidateByTechnologyRepository candidateByTechnologyRepository){
        this.candidateByTechnologyRepository = candidateByTechnologyRepository;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public void addCandidateByTechnology(Candidate candidate, Technology technology, Long experience) {
        CandidateByTechnology candidateByTechnology = candidateByTechnologyRepository.findByCandidateAndTechnology(candidate, technology);
        if(candidateByTechnology != null)
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

    public List<CandidateByTechnologyDto> getCandidatesByTechnologyByCandidate(CandidateDto candidateDto) {
        List<CandidateByTechnology> candidatesBytechnology = candidateByTechnologyRepository.findByCandidate(candidateDto);
        List<CandidateByTechnologyDto> candidatesByTechnologyDto = new ArrayList<>();
        if (candidatesBytechnology.isEmpty()) {
            throw new EmptyException("List is empty");
        }
        for (CandidateByTechnology candidateByTechnology : candidatesBytechnology) {
            candidatesByTechnologyDto.add(modelMapper().map(candidateByTechnology, CandidateByTechnologyDto.class));
        }
        return candidatesByTechnologyDto;
    }

    public List<CandidateByTechnology> getCandidatesByTechnologyByTechnology(TechnologyDto technologyDto) {
        return candidateByTechnologyRepository.findByTechnology(technologyDto);
    }

    public List<CandidateByTechnologyProjection> getCandidatesByTechnologyByNameTechnology(String nameTechnology) {
        return candidateByTechnologyRepository.findByNameTechnology(nameTechnology);
    }

}
