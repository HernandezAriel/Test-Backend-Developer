package com.testbackend.test.service;

import com.testbackend.test.model.dto.CandidateByTechnologyAddDto;
import com.testbackend.test.projection.CandidateByTechnologyProjection;


import java.util.List;

public interface CandidateByTechnologyService {

    void addTechnologyToCandidate(CandidateByTechnologyAddDto candidateByTechnologyAddDto);

    List<CandidateByTechnologyProjection> getCandidatesByTechnologyByNameTechnology(String nameTechnology);

}
