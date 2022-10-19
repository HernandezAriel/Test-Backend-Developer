package com.testbackend.test.service.imp;

import com.testbackend.test.dtoconverter.CandidateMapper;
import com.testbackend.test.dtoconverter.TechnologyMapper;
import com.testbackend.test.exception.CandidateAlreadyExistsException;
import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.exception.CandidateNotExistsException;
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

import static com.testbackend.test.dtoconverter.CandidateMapper.converterCandidateToDtoExp;
import static com.testbackend.test.dtoconverter.CandidateMapper.converterCandidateToDto;
import static com.testbackend.test.dtoconverter.CandidateMapper.converterDtoToCandidate;

import static com.testbackend.test.dtoconverter.TechnologyMapper.converterTechnologyToDto;
import static com.testbackend.test.dtoconverter.TechnologyMapper.converterDtoToTechnology;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public CandidateDto addCandidate(CandidateDto candidateDto) throws CandidateAlreadyExistsException {
        Candidate candidate = candidateRepository.findByIdCandidateOrDocumentNumber(candidateDto.getIdCandidate(), candidateDto.getDocumentNumber());
        if (candidate != null) {
            throw new CandidateAlreadyExistsException("Candidate already exists");
        } else {
            Candidate candidate1 = converterDtoToCandidate(candidateDto);
            candidateRepository.save(candidate1);
            log.info("Candidate has been created");
            return converterCandidateToDto(candidate1);
        }
    }

    public List<CandidateDto> getAllCandidates() {
        List<CandidateDto> candidatesDto = new ArrayList<>();
        for (Candidate candidate : candidateRepository.findAll()) {
            candidatesDto.add(converterCandidateToDtoExp(candidate, candidateByTechnologyServiceImp.getExperiencesByCandidate(candidate)));
        }
        return candidatesDto;
    }

    public Candidate getCandidateById(Long idCandidate) throws CandidateNotExistsException {
        return candidateRepository.findById(idCandidate).orElseThrow(() -> new CandidateNotExistsException("Candidate Not Exists"));
    }

    public CandidateDto getCandidateDtoById(Long idCandidate) throws CandidateNotExistsException {
        Candidate candidate = getCandidateById(idCandidate);
        log.debug("Candidate to Add Technology: " + candidate);
        return converterCandidateToDtoExp(candidate, candidateByTechnologyServiceImp.getExperiencesByCandidate(candidate));
    }


    public Candidate addTechnologyToCandidate(Long idCandidate, Long idTechnology, Long experience) throws CandidateNotExistsException, TechnologyNotExistsException, CandidateByTechnologyAlreadyExistsException {
        Candidate candidate = getCandidateById(idCandidate);
        Technology technology = technologyServiceImp.getTechnologyById(idTechnology);
        candidateByTechnologyServiceImp.addCandidateByTechnology(candidate, technology, experience);
        return candidate;
    }

    public Set<CandidateDto> getCandidatesByTechnology(String nameTechnology) {
        Set<CandidateDto> candidatesDto = new HashSet<>();
        List<CandidateByTechnology> candidatesForTechnologies = candidateByTechnologyServiceImp.getCandidatesByTechnologyByNameTechnology(nameTechnology);

        for (CandidateByTechnology cbt : candidatesForTechnologies) {
            List<ExperienceDto> technologies = new ArrayList<>();
            for (ExperienceDto experience : candidateByTechnologyServiceImp.getExperiencesByCandidate(cbt.getCandidate())) {
                if (experience.getName().equals(nameTechnology))
                    technologies.add(experience);
            }
            candidatesDto.add(converterCandidateToDtoExp(cbt.getCandidate(), technologies));
        }
        return candidatesDto;
    }

    public Candidate updateCandidate(Candidate candidate) throws CandidateNotExistsException {
        if (candidate.getIdCandidate() == null || getCandidateById(candidate.getIdCandidate()) == null) {
            log.error("Candidate not exists");
            throw new CandidateNotExistsException("Candidate not exists");
        } else {
            log.info("Candidate updated");
            return candidateRepository.save(candidate);
        }

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
}
