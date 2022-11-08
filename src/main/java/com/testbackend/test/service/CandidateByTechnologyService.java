package com.testbackend.test.service;

import com.testbackend.test.model.dto.CandidateByTechnologyDto;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.entity.CandidateByTechnology;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.projection.CandidateByTechnologyProjection;


import java.util.List;

public interface CandidateByTechnologyService {

    void addCandidateByTechnology(Candidate candidate, Technology technology, Long experience);

    List<CandidateByTechnologyDto> getCandidatesByTechnologyByCandidate(CandidateDto candidateDto);

    List<CandidateByTechnology> getCandidatesByTechnologyByTechnology(TechnologyDto technologyDto);

    List<CandidateByTechnologyProjection> getCandidatesByTechnologyByNameTechnology(String nameTechnology);

}
