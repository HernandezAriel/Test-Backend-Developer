package com.testbackend.test.service;

import com.testbackend.test.model.dto.CandidateByTechnologyAddDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.entity.CandidateByTechnology;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.projection.CandidateByTechnologyProjection;
import com.testbackend.test.repository.CandidateByTechnologyRepository;
import com.testbackend.test.repository.CandidateRepository;
import com.testbackend.test.repository.TechnologyRepository;
import com.testbackend.test.service.imp.CandidateByTechnologyServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getCandidateByTechnologyAddDto;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getListCandidateByTechnologyProjection;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidate;
import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnology;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getCandidateByTechnology;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CandidateByTechnologyServiceImpTest {

    private CandidateByTechnologyRepository candidateByTechnologyRepository;
    private CandidateByTechnologyServiceImp candidateByTechnologyServiceImp;
    private CandidateRepository candidateRepository;
    private TechnologyRepository technologyRepository;

    @Autowired
    CandidateService candidateService;

    @Before
    public void setUp() {
        candidateByTechnologyRepository = mock(CandidateByTechnologyRepository.class);
        candidateRepository = mock(CandidateRepository.class);
        technologyRepository = mock(TechnologyRepository.class);
        candidateService = mock(CandidateService.class);
        ModelMapper modelMapper = mock(ModelMapper.class);
        candidateByTechnologyServiceImp = new CandidateByTechnologyServiceImp(candidateByTechnologyRepository, modelMapper, candidateRepository, technologyRepository);
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
    public void getCandidateByIdOkTest() {
        CandidateByTechnologyAddDto candidateByTechnologyAddDto = getCandidateByTechnologyAddDto();
        when(candidateByTechnologyServiceImp.getCandidateById(getCandidateByTechnologyAddDto())).thenReturn(getCandidate());
        Candidate candidate = candidateByTechnologyServiceImp.getCandidateById(candidateByTechnologyAddDto);
        verify(candidateByTechnologyServiceImp, times(1)).getCandidateById(candidateByTechnologyAddDto);
        assertEquals(candidate, candidateByTechnologyServiceImp.getCandidateById(candidateByTechnologyAddDto));
    }

}
