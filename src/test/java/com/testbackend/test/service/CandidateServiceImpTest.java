package com.testbackend.test.service;

import com.testbackend.test.exception.CandidateAlreadyExistsException;
import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.repository.CandidateRepository;
import com.testbackend.test.service.imp.CandidateByTechnologyServiceImp;
import com.testbackend.test.service.imp.CandidateServiceImp;
import com.testbackend.test.service.imp.TechnologyServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static com.testbackend.test.testUtil.CandidateTestUtil.getListCandidates;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidate;
import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidateDto;
import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnology;

public class CandidateServiceImpTest {

    private CandidateRepository candidateRepository;
    private TechnologyServiceImp technologyServiceImp;
    private CandidateByTechnologyServiceImp candidateByTechnologyServiceImp;
    private CandidateServiceImp candidateServiceImp;
    private ModelMapper modelMapper;

    @Before
    public void start() {
        this.candidateRepository = mock(CandidateRepository.class);
        this.technologyServiceImp = mock(TechnologyServiceImp.class);
        this.candidateByTechnologyServiceImp = mock(CandidateByTechnologyServiceImp.class);
        modelMapper = mock(ModelMapper.class);
        this.candidateServiceImp = new CandidateServiceImp(candidateRepository, technologyServiceImp, candidateByTechnologyServiceImp, modelMapper);
    }

    @Test
    public void addCandidateOkTest() throws CandidateAlreadyExistsException {
        Candidate candidate = getCandidate();
        when(candidateRepository.save(candidate)).thenReturn(candidate);
        CandidateDto candidateDto = candidateServiceImp.addCandidate(getCandidateDto());
        assertEquals(candidateDto, candidateServiceImp.addCandidate(getCandidateDto()));
    }

    @Test
    public void addCandidateAlreadyExistsTest() {
        when(candidateRepository.findByIdCandidateOrDocumentNumber(1L, "987654321")).thenReturn(Optional.ofNullable(getCandidate()));
        Assertions.assertThrows(CandidateAlreadyExistsException.class, () -> candidateServiceImp.addCandidate(getCandidateDto()));
        verify(candidateRepository, times(1)).findByIdCandidateOrDocumentNumber(1L, "987654321");
        verify(candidateRepository, times(0)).save(getCandidate());
    }

    @Test
    public void getAllCandidatesTest() {
        List<Candidate> candidates = getListCandidates();
        when(candidateRepository.findAll()).thenReturn(candidates);
        List<CandidateDto> candidatesDto = candidateServiceImp.getAllCandidates();
        verify(candidateRepository, times(1)).findAll();
        Assertions.assertEquals(candidatesDto, candidateServiceImp.getAllCandidates());
    }

    @Test
    public void getCandidateDtoByIdOkTest() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.of(getCandidate()));
        CandidateDto candidateDto = candidateServiceImp.getCandidateDtoById(1L);
        verify(candidateRepository, times(1)).findById(1L);
        assertEquals(candidateDto, candidateServiceImp.getCandidateDtoById(1L));
    }

    @Test
    public void getCandidateByIdNotExistsTest() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CandidateNotExistsException.class, () -> candidateServiceImp.getCandidateById(1L));
        verify(candidateRepository, times(1)).findById(1L);
    }

    @Test
    public void addTechnologyToCandidateOkTest() throws TechnologyNotExistsException, CandidateByTechnologyAlreadyExistsException, CandidateNotExistsException {
        when(candidateRepository.findById(1L)).thenReturn(Optional.of(getCandidate()));
        when(technologyServiceImp.getTechnologyById(1L)).thenReturn(getTechnology());
        doNothing().when(candidateByTechnologyServiceImp).addCandidateByTechnology(getCandidate(), getTechnology(), 1L);
        Candidate candidate = candidateServiceImp.addCandidateByTechnology(1L, 1L, 1L);
        assertEquals(getCandidate(), candidate);
        verify(candidateRepository, times(1)).findById(1L);
        verify(technologyServiceImp, times(1)).getTechnologyById(1L);
        verify(candidateByTechnologyServiceImp, times(1)).addCandidateByTechnology(getCandidate(), getTechnology(), 1L);
    }

    @Test
    public void addTechnologyToCandidateCandidateNotExistsTest() throws TechnologyNotExistsException, CandidateByTechnologyAlreadyExistsException {
        when(candidateRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CandidateNotExistsException.class, () -> candidateServiceImp.addCandidateByTechnology(1L, 1L, 1L));
        verify(candidateRepository, times(1)).findById(1L);
        verify(technologyServiceImp, times(0)).getTechnologyById(1L);
        verify(candidateByTechnologyServiceImp, times(0)).addCandidateByTechnology(getCandidate(), getTechnology(), 1L);
    }

    @Test
    public void addTechnologyToCandidateCandidateByTechnologyAlreadyExistsTest() throws TechnologyNotExistsException, CandidateByTechnologyAlreadyExistsException {
        when(candidateRepository.findById(1L)).thenReturn(Optional.of(getCandidate()));
        when(technologyServiceImp.getTechnologyById(1L)).thenReturn(getTechnology());
        doThrow(CandidateByTechnologyAlreadyExistsException.class).when(candidateByTechnologyServiceImp).addCandidateByTechnology(getCandidate(), getTechnology(), 1L);
        assertThrows(CandidateByTechnologyAlreadyExistsException.class, () -> candidateServiceImp.addCandidateByTechnology(1L, 1L, 1L));
        verify(candidateRepository, times(1)).findById(1L);
        verify(technologyServiceImp, times(1)).getTechnologyById(1L);
        verify(candidateByTechnologyServiceImp, times(1)).addCandidateByTechnology(getCandidate(), getTechnology(), 1L);
    }

    @Test
    public void deleteCandidateOkTest() throws CandidateNotExistsException {
        when(candidateRepository.findById(1L)).thenReturn(Optional.of(getCandidate()));
        doNothing().when(candidateRepository).deleteById(1L);
        candidateServiceImp.deleteCandidate(1L);
        verify(candidateRepository, times(1)).findById(1L);
        verify(candidateRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteCandidateNotExistsTest() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CandidateNotExistsException.class, () -> candidateServiceImp.deleteCandidate(1L));
        verify(candidateRepository, times(1)).findById(1L);
        verify(candidateByTechnologyServiceImp, times(0)).getCandidatesByTechnologyByCandidate(getCandidateDto());
        verify(candidateRepository, times(0)).deleteById(1L);
    }


}
