package com.testbackend.test.service.Imp;

import com.testbackend.test.exception.CandidateAlreadyExistsException;
import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.repository.CandidateRepository;
import com.testbackend.test.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.testbackend.test.dtoconverter.CandidateToCandidateDto.converter;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CandidateServiceImp implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private TechnologyServiceImp technologyServiceImp;
    @Autowired
    private CandidateByTechnologyServiceImp candidateByTechnologyServiceImp;

    private ModelMapper modelMapper=new ModelMapper();

    public Candidate addCandidate(Candidate candidate) throws CandidateAlreadyExistsException {
        if ((candidateRepository.findByIdCandidateOrDocument(candidate.getIdCandidate(), candidate.getDocumentNumber())) != null) {
            throw new CandidateAlreadyExistsException("Candidate already exists");
        } else {
            log.info("Candidate has been created");
            return candidateRepository.save(candidate);
        }
    }

    public List<CandidateDto> getAllCandidates(){
        List<CandidateDto> candidatesDto = new ArrayList<>();
        for(Candidate candidate : candidateRepository.findAll()){
            candidatesDto.add(converter(candidate, candidateByTechnologyServiceImp.getExperiencesByCandidate(candidate)));
        }
        return candidatesDto;
    }
    public Candidate getCandidateById(Long idCandidate) throws CandidateNotExistsException {
        return candidateRepository.findById(idCandidate).orElseThrow (() -> new CandidateNotExistsException("Candidate Not Exists"));
    }

    public CandidateDto getCandidateDtoById(Long idCandidate) throws CandidateNotExistsException{
        Candidate candidate = getCandidateById(idCandidate);
        log.debug("Candidate to Add Technology: " + candidate);
        return converter(candidate, candidateByTechnologyServiceImp.getExperiencesByCandidate(candidate));
    }


    public Candidate addTechnologyToCandidate(Long idCandidate, Long idTechnology, Long experience) throws CandidateNotExistsException, TechnologyNotExistsException, CandidateByTechnologyAlreadyExistsException {
        Candidate candidate = getCandidateById(idCandidate);
        Technology technology = technologyServiceImp.getTechnologyById(idTechnology);
        candidateByTechnologyServiceImp.addCandidateByTechnology(candidate, technology, experience);
        return candidate;
    }

    public Candidate updateCandidate(Candidate candidate) throws CandidateNotExistsException {
        if(candidate.getIdCandidate() == null || getCandidateById(candidate.getIdCandidate()) == null){
            log.error("Candidate not exists");
            throw new CandidateNotExistsException("Candidate not exists");
        }
        else{
            log.info("Candidate updated");
            return candidateRepository.save(candidate);
        }

    }

    public void deleteCandidate(Long idCandidate) throws CandidateNotExistsException  {
        Candidate candidate = getCandidateById(idCandidate);
        if(!candidateByTechnologyServiceImp.getCandidatesByTechnologyByCandidate(candidate).isEmpty()){
            log.error("Candidate not exists");
            throw new CandidateNotExistsException("Candidate not exists");
        }

        else{
            log.info("Candidate deleted");
            candidateRepository.deleteById(idCandidate);
        }

    }
}
