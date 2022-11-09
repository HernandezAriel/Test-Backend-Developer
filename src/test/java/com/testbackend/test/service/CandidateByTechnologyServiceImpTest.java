package com.testbackend.test.service;

import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.entity.CandidateByTechnology;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.repository.CandidateByTechnologyRepository;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidate;
import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnology;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getCandidateByTechnology;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CandidateByTechnologyServiceImpTest {

    private CandidateByTechnologyRepository candidateByTechnologyRepository;


    @Before
    public void setUp() {
        candidateByTechnologyRepository = mock(CandidateByTechnologyRepository.class);
    }

    @Test
    public void addTechnologyToCandidate() {
        Candidate candidate = getCandidate();
        Technology technology = getTechnology();
        CandidateByTechnology candidateByTechnology = getCandidateByTechnology();
        when(candidateByTechnologyRepository.save(candidateByTechnology)).thenReturn(candidateByTechnology);
        verify(candidateByTechnologyRepository, times(1)).save(getCandidateByTechnology());
    }
}
