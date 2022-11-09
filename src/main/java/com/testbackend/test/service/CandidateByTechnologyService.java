package com.testbackend.test.service;

import com.testbackend.test.model.dto.CandidateByTechnologyAddDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.projection.CandidateByTechnologyProjection;


import java.util.List;

public interface CandidateByTechnologyService {

    CandidateByTechnologyAddDto addTechnologyToCandidate(CandidateByTechnologyAddDto candidateByTechnologyAddDto);

    List<CandidateByTechnologyProjection> getCandidatesByTechnologyByNameTechnology(String nameTechnology);

    Candidate getCandidateById(CandidateByTechnologyAddDto candidateByTechnologyAddDto);

    Technology getTechnologyById(CandidateByTechnologyAddDto candidateByTechnologyAddDto);


}
