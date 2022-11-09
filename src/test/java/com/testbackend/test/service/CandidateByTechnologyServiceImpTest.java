package com.testbackend.test.service;

import com.testbackend.test.model.dto.CandidateByTechnologyAddDto;
import com.testbackend.test.model.dto.CandidateByTechnologyDto;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.dto.ExperienceDto;
import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.entity.CandidateByTechnology;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.projection.CandidateByTechnologyProjection;
import com.testbackend.test.repository.CandidateByTechnologyRepository;
import com.testbackend.test.repository.CandidateRepository;
import com.testbackend.test.repository.TechnologyRepository;
import com.testbackend.test.service.imp.CandidateByTechnologyServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getCandidateByTechnologyAddDto;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getListCandidateByTechnologyProjection;
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
    private CandidateRepository candidateRepository;
    private TechnologyRepository technologyRepository;
    private ModelMapper modelMapper;

    @Autowired
    CandidateService candidateService;

    @Before
    public void setUp() {
        candidateByTechnologyRepository = mock(CandidateByTechnologyRepository.class);
        candidateRepository = mock(CandidateRepository.class);
        technologyRepository = mock(TechnologyRepository.class);
        candidateService = mock(CandidateService.class);
        modelMapper = mock(ModelMapper.class);
        candidateByTechnologyServiceImp = new CandidateByTechnologyServiceImp(candidateByTechnologyRepository, modelMapper, candidateRepository, technologyRepository );
    }


    @Test
    public void addTechnologyToCandidateTest() {
        CandidateByTechnology candidateByTechnology = getCandidateByTechnology();
        CandidateByTechnologyAddDto candidateByTechnologyAddDto = getCandidateByTechnologyAddDto();
        Candidate candidate = getCandidate();
        Technology technology = getTechnology();

        when(candidateRepository.findById(candidateByTechnologyAddDto.getTechnologyId())).thenReturn(Optional.ofNullable(candidate));
        when(technologyRepository.findById(candidateByTechnologyAddDto.getTechnologyId())).thenReturn(Optional.ofNullable(technology));
        when(candidateByTechnologyRepository.save(candidateByTechnology)).thenReturn(candidateByTechnology);
        CandidateByTechnologyAddDto candidateByTechnologyAddDto1 = candidateByTechnologyServiceImp.addTechnologyToCandidate(candidateByTechnologyAddDto);
        assertEquals(candidateByTechnologyAddDto1, candidateByTechnologyServiceImp.addTechnologyToCandidate(candidateByTechnologyAddDto));

    }

    @Test
    public void getCandidatesByTechnologyByNameTechnologyTest() {
        List<CandidateByTechnologyProjection> candidateByTechnologyProjection = getListCandidateByTechnologyProjection();
        String name = "java";
        when(candidateByTechnologyRepository.findByNameTechnology(name)).thenReturn(candidateByTechnologyProjection);
        assertEquals(candidateByTechnologyServiceImp.getCandidatesByTechnologyByNameTechnology(name), candidateByTechnologyProjection);
    }

    @Test
    public void getCandidateDtoByIdOkTest() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.of(getCandidate()));
        CandidateDto candidateDto = candidateService.getCandidateDtoById(1L);
        verify(candidateRepository, times(1)).findById(1L);
        assertEquals(candidateDto, candidateService.getCandidateDtoById(1L));
    }
}
