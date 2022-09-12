package com.testbackend.test.services;

import com.testbackend.test.models.dtos.ExperienceDto;
import com.testbackend.test.models.entities.Candidate;
import com.testbackend.test.models.entities.CandidateByTechnology;
import com.testbackend.test.models.entities.Technology;

import java.util.List;

public interface ICandidateByTechnologyService {

    void addCandidateByTechnology(Candidate candidate, Technology technology, Long experience);
    List<CandidateByTechnology> getCandidatesByTechnologyByCandidate(Candidate candidate);
    List<ExperienceDto> getExperiencesByCandidate(Candidate candidate);
    List<CandidateByTechnology> getCandidatesByTechnologyByTechnology(Technology technology);
}
