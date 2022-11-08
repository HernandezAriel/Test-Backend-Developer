package com.testbackend.test.service.imp;

import com.testbackend.test.exception.EmptyException;
import com.testbackend.test.model.dto.CandidateByTechnologyDto;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.dto.TechnologyDto;
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
    private final CandidateService candidateService;
    private final TechnologyService technologyService;

    @Autowired
    public CandidateByTechnologyServiceImp(CandidateByTechnologyRepository candidateByTechnologyRepository, CandidateService candidateService, TechnologyService technologyService) {
        this.candidateByTechnologyRepository = candidateByTechnologyRepository;
        this.candidateService = candidateService;
        this.technologyService = technologyService;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    public CandidateByTechnologyDto addCandidateByTechnology(CandidateByTechnologyDto candidateByTechnologyDto) {
//        var optionalCandidate = candidateService.getCandidateById(candidateByTechnologyDto.getCandidate().getIdCandidate());
//        var optionalTechnology = technologyService.getTechnologyById(candidateByTechnologyDto.getTechnology().getIdTechnology());
//        CandidateByTechnology candidateByTechnology;
//        candidateByTechnology.setCandidate(modelMapper().map(candidateByTechnologyDto.getCandidate(), Candidate.class));
//        candidateByTechnologyRepository.save(candidateByTechnology);
//        log.info("Se creo el CandidateByTechnology con exito");
//        return CandidateByTechnologyMapper.mapModelToDto(candidateByTechnology);
//    }

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

    public List<CandidateByTechnology> getCandidatesByTechnologyByNameTechnology(String nameTechnology) {
        return candidateByTechnologyRepository.findByNameTechnology(nameTechnology);
    }

}
