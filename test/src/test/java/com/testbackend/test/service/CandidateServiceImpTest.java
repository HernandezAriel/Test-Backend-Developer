package com.testbackend.test.service;

import com.testbackend.test.exception.CandidateAlreadyExistsException;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.repository.CandidateRepository;
import com.testbackend.test.service.Imp.CandidateByTechnologyServiceImp;
import com.testbackend.test.service.Imp.CandidateServiceImp;
import com.testbackend.test.service.Imp.TechnologyServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidate;

public class CandidateServiceImpTest {

    @InjectMocks
    CandidateRepository candidateRepository;
    @Mock
    private TechnologyServiceImp technologyServiceImp;
    @Mock
    private CandidateByTechnologyServiceImp candidateByTechnologyServiceImp;
    @Mock
    private CandidateServiceImp candidateServiceImp;

//    @Before
//    public void start() {
//        this.candidateRepository = mock(CandidateRepository.class);
//        this.technologyServiceImp = mock(TechnologyServiceImp.class);
//        this.candidateByTechnologyServiceImp = mock(CandidateByTechnologyServiceImp.class);
//        this.candidateServiceImp = new CandidateServiceImp(candidateRepository, technologyServiceImp, candidateByTechnologyServiceImp);
//    }

    @Test
    public void addCandidateOkTest() throws CandidateAlreadyExistsException {
        when(candidateRepository.findByDocumentNumber("987654321")).thenReturn(null);
        when(candidateRepository.save(getCandidate())).thenReturn(getCandidate());

        Candidate candidate = candidateServiceImp.addCandidate(getCandidate());

        assertNotNull(candidate);
        assertEquals(getCandidate(), candidate);
        verify(candidateRepository, times(1)).findByDocumentNumber("987654321");
        verify(candidateRepository, times(1)).save(getCandidate());
    }

}
