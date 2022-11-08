package com.testbackend.test.service.imp;

import com.testbackend.test.exception.CandidateAlreadyExistsException;
import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.exception.EmptyException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.dto.ExperienceDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.entity.CandidateByTechnology;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.repository.CandidateRepository;
import com.testbackend.test.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CandidateServiceImp implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final TechnologyServiceImp technologyServiceImp;
    private final CandidateByTechnologyServiceImp candidateByTechnologyServiceImp;
    private final ModelMapper modelMapper;

    @Autowired
    public CandidateServiceImp(CandidateRepository candidateRepository, TechnologyServiceImp technologyServiceImp, CandidateByTechnologyServiceImp candidateByTechnologyServiceImp, ModelMapper modelMapper) {
        this.candidateRepository = candidateRepository;
        this.technologyServiceImp = technologyServiceImp;
        this.candidateByTechnologyServiceImp = candidateByTechnologyServiceImp;
        this.modelMapper = modelMapper;
    }

    public CandidateDto addCandidate(CandidateDto candidateDto) {
        if (candidateRepository.findByIdCandidateOrDocumentNumber(candidateDto.getIdCandidate(), candidateDto.getDocumentNumber()).isPresent()) {
            throw new CandidateAlreadyExistsException("Candidate already exists");
        } else {
            candidateRepository.save(modelMapper.map(candidateDto, Candidate.class));
            log.info("Candidate has been created");
            return candidateDto;
        }
    }

    public List<CandidateDto> getAllCandidates() {
        List<Candidate> candidates = candidateRepository.findAll();
        List<CandidateDto> candidatesDto = new ArrayList<>();
        if (candidates.isEmpty()) {
            throw new EmptyException("List is empty");
        }
        for (Candidate candidate : candidates) {
            candidatesDto.add(modelMapper.map(candidate, CandidateDto.class));
        }
        return candidatesDto;
    }

    public Candidate getCandidateById(Long idCandidate) throws CandidateNotExistsException {
        return candidateRepository.findById(idCandidate)
                .orElseThrow(() -> new CandidateNotExistsException("Candidate Not Exists"));
    }

    public CandidateDto getCandidateDtoById(Long idCandidate) throws CandidateNotExistsException {
        Candidate candidate = getCandidateById(idCandidate);
        log.debug("Candidate to Add Technology: " + candidate);
        return modelMapper.map(candidate, CandidateDto.class);
    }


//    public Set<CandidateDto> getCandidatesByTechnology(String nameTechnology) {
//        Set<CandidateDto> candidatesDto = new HashSet<>();
//        List<CandidateByTechnology> candidatesForTechnologies = candidateByTechnologyServiceImp.getCandidatesByTechnologyByNameTechnology(nameTechnology);
//
//        for (CandidateByTechnology cbt : candidatesForTechnologies) {
//            List<ExperienceDto> technologies = new ArrayList<>();
//            for (ExperienceDto experience : candidateByTechnologyServiceImp.getExperiencesByCandidate(cbt.getCandidate())) {
//                if (experience.getName().equals(nameTechnology)) technologies.add(experience);
//            }
//            candidatesDto.add(converterCandidateToDtoExp(cbt.getCandidate(), technologies));
//        }
//        return candidatesDto;
//    }

    public void updateCandidate(CandidateDto candidateDto, Long id) {
        candidateRepository.save(modelMapper.map(candidateDto, getCandidateById(id).getClass()));
    }

    public void deleteCandidate(Long idCandidate) throws CandidateNotExistsException {
        Candidate candidate = getCandidateById(idCandidate);
        if (!candidateByTechnologyServiceImp.getCandidatesByTechnologyByCandidate(candidate).isEmpty()) {
            log.error("Candidate not exists");
            throw new CandidateNotExistsException("Candidate not exists");
        } else {
            log.info("Candidate deleted");
            candidateRepository.deleteById(idCandidate);
        }
    }

    public Candidate addTechnologyToCandidate(Long idCandidate, Long idTechnology, Long experience) throws CandidateNotExistsException, TechnologyNotExistsException, CandidateByTechnologyAlreadyExistsException {
        Candidate candidate = getCandidateById(idCandidate);
        Technology technology = technologyServiceImp.getTechnologyById(idTechnology);
        candidateByTechnologyServiceImp.addCandidateByTechnology(candidate, technology, experience);
        return candidate;
    }
}

