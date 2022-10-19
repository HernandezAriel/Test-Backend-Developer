package com.testbackend.test.service;

import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;

import com.testbackend.test.model.dto.ExperienceDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.entity.CandidateByTechnology;
import com.testbackend.test.model.entity.Technology;

import java.util.List;

public interface CandidateByTechnologyService {

    void addCandidateByTechnology(Candidate candidateDto, Technology technologyDto, Long experience) throws CandidateByTechnologyAlreadyExistsException;

    List<CandidateByTechnology> getCandidatesByTechnologyByCandidate(Candidate candidate);

    List<ExperienceDto> getExperiencesByCandidate(Candidate candidate);

    List<CandidateByTechnology> getCandidatesByTechnologyByTechnology(Technology technology);

    List<CandidateByTechnology> getCandidatesByTechnologyByNameTechnology(String nameTechnology);
}
