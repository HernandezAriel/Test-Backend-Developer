package com.testbackend.test.service.imp;

import com.testbackend.test.exception.CandidateAlreadyExistsException;
import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.exception.EmptyException;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.repository.CandidateRepository;
import com.testbackend.test.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CandidateServiceImp implements CandidateService {
    private final CandidateRepository candidateRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Autowired
    public CandidateServiceImp(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateDto addCandidate(CandidateDto candidateDto) {
        if (candidateRepository.findByIdCandidateOrDocumentNumber(candidateDto.getIdCandidate(), candidateDto.getDocumentNumber()).isPresent()) {
            throw new CandidateAlreadyExistsException("Candidate already exists");
        } else {
            candidateRepository.save(modelMapper().map(candidateDto, Candidate.class));
            log.info("Candidate has been created");
            return candidateDto;
        }
    }

    public List<CandidateDto> getAllCandidates() {
        List<Candidate> candidates = candidateRepository.findAll();
        List<CandidateDto> candidatesDto = new ArrayList<>();
        if (candidates.isEmpty()) {
            //log.error("List is empty", ex);
            throw new EmptyException("List is empty");
        }
        for (Candidate candidate : candidates) {
            candidatesDto.add(modelMapper().map(candidate, CandidateDto.class));
        }
        return candidatesDto;
    }

    public Candidate getCandidateById(Long idCandidate) {
        return candidateRepository.findById(idCandidate)
                .orElseThrow(() -> new CandidateNotExistsException("Candidate Not Exists"));
        //log.error("Candidate Not Exists", ex);
    }

    public CandidateDto getCandidateDtoById(Long idCandidate) {
        Candidate candidate = getCandidateById(idCandidate);
        log.debug("Candidate to Add Technology: " + candidate);
        return modelMapper().map(candidate, CandidateDto.class);
    }

    public void updateCandidate(CandidateDto candidateDto, Long id) {
        candidateRepository.save(modelMapper().map(candidateDto, getCandidateById(id).getClass()));
    }

    public void deleteCandidate(Long idCandidate) {
        candidateRepository.deleteById(getCandidateById(idCandidate).getIdCandidate());
    }
}

