package com.testbackend.test.service;

import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.model.dto.CandidateByTechnologyDto;
import com.testbackend.test.model.dto.ExperienceDto;
import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.model.entity.CandidateByTechnology;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.repository.CandidateByTechnologyRepository;
import com.testbackend.test.service.imp.CandidateByTechnologyServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnologyDto;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidate;
import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnology;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getListExperienceDto;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getCandidateByTechnology;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getListCandidateByTechnology;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getCandidateByTechnology;
import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidate;
import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidateDto;
import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnology;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CandidateByTechnologyServiceImpTest {

    private CandidateByTechnologyRepository candidateByTechnologyRepository;
    private CandidateByTechnologyServiceImp candidateByTechnologyServiceImp;

    @Before
    public void start() {
        candidateByTechnologyRepository = mock(CandidateByTechnologyRepository.class);
        candidateByTechnologyServiceImp = new CandidateByTechnologyServiceImp(candidateByTechnologyRepository);
    }

    @Test
    public void addCandidateByTechnologyAlreadyExistsTest() {
        when(candidateByTechnologyRepository.findByCandidateAndTechnology(getCandidate(), getTechnology())).thenReturn(getCandidateByTechnology());
        Assert.assertThrows(CandidateByTechnologyAlreadyExistsException.class, () -> candidateByTechnologyServiceImp.addCandidateByTechnology(getCandidate(), getTechnology(), 1L));
        verify(candidateByTechnologyRepository, times(1)).findByCandidateAndTechnology(getCandidate(), getTechnology());
        verify(candidateByTechnologyRepository, times(0)).save(getCandidateByTechnology());
    }


    @Test
    public void getCandidatesByTechnologyByTechnologyOkTest() {
        when(candidateByTechnologyRepository.findByTechnology(getTechnologyDto())).thenReturn(getListCandidateByTechnology());
        List<CandidateByTechnology> cbt = candidateByTechnologyServiceImp.getCandidatesByTechnologyByTechnology(getTechnologyDto());
        Assertions.assertEquals(getListCandidateByTechnology().size(), cbt.size());
        Assertions.assertEquals(getListCandidateByTechnology().get(0), cbt.get(0));
        verify(candidateByTechnologyRepository, times(1)).findByTechnology(getTechnologyDto());
    }
}
