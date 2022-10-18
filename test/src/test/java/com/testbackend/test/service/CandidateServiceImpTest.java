package com.testbackend.test.service;

import com.testbackend.test.exception.CandidateAlreadyExistsException;
import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.repository.CandidateRepository;
import com.testbackend.test.service.Imp.CandidateByTechnologyServiceImp;
import com.testbackend.test.service.Imp.CandidateServiceImp;
import com.testbackend.test.service.Imp.TechnologyServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidate;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getListExperienceDto;

public class CandidateServiceImpTest {

    private CandidateRepository candidateRepository;
    private TechnologyServiceImp technologyServiceImp;
    private CandidateByTechnologyServiceImp candidateByTechnologyServiceImp;
    private CandidateServiceImp candidateServiceImp;

    @Before
    public void start() {
        this.candidateRepository = mock(CandidateRepository.class);
        this.technologyServiceImp = mock(TechnologyServiceImp.class);
        this.candidateByTechnologyServiceImp = mock(CandidateByTechnologyServiceImp.class);
        this.candidateServiceImp = new CandidateServiceImp(candidateRepository, technologyServiceImp, candidateByTechnologyServiceImp);
    }

    @Test
    public void addCandidateOkTest() throws CandidateAlreadyExistsException {
        when(candidateRepository.findByIdCandidateOrDocumentNumber(1L, "987654321")).thenReturn(null);
        when(candidateRepository.save(getCandidate())).thenReturn(getCandidate());
        Candidate candidate = candidateServiceImp.addCandidate(getCandidate());
        assertNotNull(candidate);
        assertEquals(getCandidate(), candidate);
        verify(candidateRepository, times(1)).findByIdCandidateOrDocumentNumber(1L, "987654321");
        verify(candidateRepository, times(1)).save(getCandidate());
    }

    @Test
    public void addCandidateAlreadyExistsTest() {
        when(candidateRepository.findByIdCandidateOrDocumentNumber(1L, "987654321")).thenReturn(getCandidate());
        Assertions.assertThrows(CandidateAlreadyExistsException.class, () -> candidateServiceImp.addCandidate(getCandidate()));
        verify(candidateRepository, times(1)).findByIdCandidateOrDocumentNumber(1L, "987654321");
        verify(candidateRepository, times(0)).save(getCandidate());
    }

    @Test
    public void getCandidateByIdOkTest() throws CandidateNotExistsException {
        when(candidateRepository.findById(1L)).thenReturn(Optional.of(getCandidate()));
        Candidate candidate = candidateServiceImp.getCandidateById(1L);
        assertEquals(getCandidate(), candidate);
        verify(candidateRepository, times(1)).findById(1L);
    }

    @Test
    public void getCandidateByIdNotExistsTest() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CandidateNotExistsException.class, () -> candidateServiceImp.getCandidateById(1L));
        verify(candidateRepository, times(1)).findById(1L);
    }


}
