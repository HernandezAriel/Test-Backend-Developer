package com.testbackend.test.service.imp;

import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.CandidateByTechnologyAddDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.projection.CandidateByTechnologyProjection;
import com.testbackend.test.repository.CandidateRepository;
import com.testbackend.test.repository.TechnologyRepository;
import com.testbackend.test.service.CandidateByTechnologyService;
import lombok.extern.slf4j.Slf4j;
import com.testbackend.test.model.entity.CandidateByTechnology;
import com.testbackend.test.repository.CandidateByTechnologyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CandidateByTechnologyServiceImp implements CandidateByTechnologyService {

    private final CandidateByTechnologyRepository candidateByTechnologyRepository;
    private final ModelMapper modelMapper;

    CandidateRepository candidateRepository;

    TechnologyRepository technologyRepository;

    @Autowired
    public CandidateByTechnologyServiceImp(CandidateByTechnologyRepository candidateByTechnologyRepository, ModelMapper modelMapper, CandidateRepository candidateRepository, TechnologyRepository technologyRepository) {
        this.candidateByTechnologyRepository = candidateByTechnologyRepository;
        this.modelMapper = modelMapper;
        this.candidateRepository = candidateRepository;
        this.technologyRepository = technologyRepository;
    }

    public CandidateByTechnologyAddDto addTechnologyToCandidate(CandidateByTechnologyAddDto candidateByTechnologyAddDto) {
        Candidate candidate = getCandidateById(candidateByTechnologyAddDto);
        Technology technology = getTechnologyById(candidateByTechnologyAddDto);
        CandidateByTechnology candidateByTechnology = CandidateByTechnology.builder()
                .candidate(candidate)
                .technology(technology)
                .experience(candidateByTechnologyAddDto.getExperience())
                .build();
        candidateByTechnologyRepository.save(candidateByTechnology);
        log.info("CandidateByTechnology created");
        return modelMapper.map(candidateByTechnology, CandidateByTechnologyAddDto.class);
    }

    public List<CandidateByTechnologyProjection> getCandidatesByTechnologyByNameTechnology(String nameTechnology) {
        return candidateByTechnologyRepository.findByNameTechnology(nameTechnology);
    }

    public Candidate getCandidateById(CandidateByTechnologyAddDto candidateByTechnologyAddDto) {
        return candidateRepository.findById(candidateByTechnologyAddDto.getCandidateId())
                .orElseThrow(() -> {
                    log.error("Candidate Not Exists");
                    throw new CandidateNotExistsException("Id candidate not found.");
                });
    }

    public Technology getTechnologyById(CandidateByTechnologyAddDto candidateByTechnologyAddDto) {
        return technologyRepository.findById(candidateByTechnologyAddDto.getTechnologyId())
                .orElseThrow(() -> {
                    log.error("Technology not exists");
                    throw new TechnologyNotExistsException("Id Technology not found");
                });
    }

}

