package com.testbackend.test.service;

import com.testbackend.test.model.dto.CandidateByTechnologyDto;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.model.entity.CandidateByTechnology;


import java.util.List;

public interface CandidateByTechnologyService {

    void addCandidateByTechnology(CandidateDto candidateDto, TechnologyDto technologyDto, Long experience);

    List<CandidateByTechnologyDto> getCandidatesByTechnologyByCandidate(CandidateDto candidateDto);

    List<CandidateByTechnology> getCandidatesByTechnologyByTechnology(TechnologyDto technologyDto);

    List<CandidateByTechnology> getCandidatesByTechnologyByNameTechnology(String nameTechnology);
}
