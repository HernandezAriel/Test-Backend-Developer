package com.testbackend.test.controller;

import com.testbackend.test.model.dto.CandidateByTechnologyAddDto;
import com.testbackend.test.projection.CandidateByTechnologyProjection;
import com.testbackend.test.service.CandidateByTechnologyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getCandidateByTechnologyAddDto;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getListCandidateByTechnologyProjection;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CandidateByTechnologyControllerTest {

    private CandidateByTechnologyService candidateByTechnologyService;
    private CandidateByTechnologyController candidateByTechnologyController;

    @Before
    public void setUp() {
        candidateByTechnologyService = mock(CandidateByTechnologyService.class);
        candidateByTechnologyController = new CandidateByTechnologyController(candidateByTechnologyService);
    }

    @Test
    public void addTechnologyToCandidateControllerTest() {
        CandidateByTechnologyAddDto candidateByTechnologyAddDto = getCandidateByTechnologyAddDto();
        when(candidateByTechnologyService.addTechnologyToCandidate(candidateByTechnologyAddDto)).thenReturn(candidateByTechnologyAddDto);
        ResponseEntity<String> response = candidateByTechnologyController.addTechnologyToCandidate(candidateByTechnologyAddDto);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(candidateByTechnologyService, times(1)).addTechnologyToCandidate(candidateByTechnologyAddDto);
    }

    @Test
    public void findCandidatesByTechnologiesTest() {
        List<CandidateByTechnologyProjection> candidateByTechnologyProjection = getListCandidateByTechnologyProjection();
        String name = "java";
        when(candidateByTechnologyService.getCandidatesByTechnologyByNameTechnology(name)).thenReturn(candidateByTechnologyProjection);
        ResponseEntity<List<CandidateByTechnologyProjection>> response = candidateByTechnologyController.findCandidatesByTechnologies(name);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(candidateByTechnologyService, times(1)).getCandidatesByTechnologyByNameTechnology(name);
    }

}
