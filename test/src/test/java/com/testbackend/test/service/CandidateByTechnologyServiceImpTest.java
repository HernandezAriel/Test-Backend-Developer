package com.testbackend.test.service;

import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.model.entity.CandidateByTechnology;
import com.testbackend.test.repository.CandidateByTechnologyRepository;
import com.testbackend.test.service.imp.CandidateByTechnologyServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.mockito.Mockito.mock;
import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidate;
import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnology;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getCandidateByTechnology;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getListCandidateByTechnology;
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

    @Ignore
    @Test
    public void addCandidateByTechnologyOkTest() throws CandidateByTechnologyAlreadyExistsException {
        when(candidateByTechnologyRepository.findByCandidateAndTechnology(getCandidate(), getTechnology())).thenReturn(null);
        when(candidateByTechnologyRepository.save(getCandidateByTechnology())).thenReturn(getCandidateByTechnology());
        candidateByTechnologyServiceImp.addCandidateByTechnology(getCandidate(), getTechnology(), 1L);
        verify(candidateByTechnologyRepository, times(1)).findByCandidateAndTechnology(getCandidate(), getTechnology());
        verify(candidateByTechnologyRepository, times(1)).save(getCandidateByTechnology());
    }

    @Test
    public void addCandidateForTechnologyAlreadyExistsTest() {
        when(candidateByTechnologyRepository.findByCandidateAndTechnology(getCandidate(), getTechnology())).thenReturn(getCandidateByTechnology());
        Assert.assertThrows(CandidateByTechnologyAlreadyExistsException.class, () -> candidateByTechnologyServiceImp.addCandidateByTechnology(getCandidate(), getTechnology(), 1L));
        verify(candidateByTechnologyRepository, times(1)).findByCandidateAndTechnology(getCandidate(), getTechnology());
        verify(candidateByTechnologyRepository, times(0)).save(getCandidateByTechnology());
    }

    @Test
    public void getCandidatesForTechnologyByCandidateOkTest() {
        when(candidateByTechnologyRepository.findByCandidate(getCandidate())).thenReturn(getListCandidateByTechnology());
        List<CandidateByTechnology> cxt = candidateByTechnologyServiceImp.getCandidatesByTechnologyByCandidate(getCandidate());
        Assertions.assertEquals(getListCandidateByTechnology().size(), cxt.size());
        Assertions.assertEquals(getListCandidateByTechnology().get(0), cxt.get(0));
        verify(candidateByTechnologyRepository, times(1)).findByCandidate(getCandidate());
    }

}
