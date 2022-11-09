package com.testbackend.test.controller;

import com.google.gson.Gson;
import com.testbackend.test.exception.CandidateAlreadyExistsException;
import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.util.ResponseMessage;
import com.testbackend.test.service.CandidateService;
import com.testbackend.test.service.imp.CandidateServiceImp;
import com.testbackend.test.util.UrlBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidateDtoUpdate;
import static com.testbackend.test.testUtil.CandidateTestUtil.getListCandidateDto;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidate;
import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidateDto;
import static com.testbackend.test.testUtil.CandidateTestUtil.getSetCandidateDto;
import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnology;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getListExperienceDto;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getCandidateByTechnology;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getListCandidateByTechnology;


public class CandidateControllerTest {

    private CandidateService candidateService;
    private CandidateController candidateController;


    @Before
    public void setUp() {
        candidateService = mock(CandidateService.class);
        candidateController = new CandidateController(candidateService);
    }

    @Test
    public void addCandidateTest() throws Exception {
        CandidateDto candidateDto = getCandidateDto();
        CandidateDto addCandidateDto = getCandidateDto();
        when(candidateService.addCandidate(addCandidateDto)).thenReturn(candidateDto);
        ResponseEntity<String> response = candidateController.addCandidate(candidateDto);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(candidateService, times(1)).addCandidate(candidateDto);
    }

    @Test
    public void getAllCandidatesTest() {
        List<CandidateDto> candidates = getListCandidateDto();
        when(candidateService.getAllCandidates()).thenReturn(candidates);
        ResponseEntity<List<CandidateDto>> response = candidateController.getAllCandidates();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(candidateService, times(1)).getAllCandidates();
    }

    @Test
    public void getCandidateByIdOkTest() throws CandidateNotExistsException {
        when(candidateService.getCandidateDtoById(1L)).thenReturn(getCandidateDto());
        ResponseEntity<CandidateDto> response = candidateController.getCandidateById(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(getCandidateDto(), response.getBody());
        verify(candidateService, times(1)).getCandidateDtoById(1L);
    }


    @Test
    public void deleteCandidateOkTest() throws CandidateNotExistsException {
        doNothing().when(candidateService).deleteCandidate(1L);
        ResponseEntity<String> response = candidateController.deleteCandidate(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(candidateService, times(1)).deleteCandidate(1L);
    }

}


