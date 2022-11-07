package com.testbackend.test.controller;

import com.testbackend.test.exception.CandidateAlreadyExistsException;
import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.util.ResponseMessage;
import com.testbackend.test.service.imp.CandidateServiceImp;
import com.testbackend.test.util.UrlBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
import java.util.Set;

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

    private CandidateServiceImp candidateServiceImp;
    private CandidateController candidateController;

    @Before
    public void setUp() {
        candidateServiceImp = mock(CandidateServiceImp.class);
        candidateController = new CandidateController(candidateServiceImp);
    }

    @Test
    public void addCandidateOkTest() throws CandidateAlreadyExistsException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(candidateServiceImp.addCandidate(getCandidateDto())).thenReturn(getCandidateDto());
        ResponseEntity<ResponseMessage> response = candidateController.addCandidate(getCandidateDto());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(UrlBuilder.buildURL("candidates", getCandidate().getIdCandidate()).toString()
                , Objects.requireNonNull(response.getHeaders().get("Location")).get(0));
        verify(candidateServiceImp, times(1)).addCandidate(getCandidateDto());
    }

    @Test
    public void getCandidateByIdOkTest() throws CandidateNotExistsException {
        when(candidateServiceImp.getCandidateDtoById(1L)).thenReturn(getCandidateDto());
        ResponseEntity<CandidateDto> response = candidateController.getCandidateById(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(getCandidateDto(), response.getBody());
        verify(candidateServiceImp, times(1)).getCandidateDtoById(1L);
    }

    @Test
    public void addTechnologyToCandidateOkTest() throws TechnologyNotExistsException, CandidateByTechnologyAlreadyExistsException, CandidateNotExistsException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(candidateServiceImp.addTechnologyToCandidate(1L, 1L, 1L)).thenReturn(getCandidate());
        ResponseEntity<ResponseMessage> response = candidateController.addTechnologyToCandidate(1L, 1L, 1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(UrlBuilder.buildURL("candidates", getCandidate().getIdCandidate()).toString()
                , Objects.requireNonNull(response.getHeaders().get("Location")).get(0));
        verify(candidateServiceImp, times(1)).addTechnologyToCandidate(1L, 1L, 1L);
    }

    @Test
    public void getCandidatesByTechnologyOkTest(){
        when(candidateServiceImp.getCandidatesByTechnology("Java")).thenReturn(getSetCandidateDto());
        ResponseEntity<Set<CandidateDto>> response = candidateController.getCandidatesByTechnology("Java");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(getSetCandidateDto().size(), Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    public void deleteCandidateOkTest() throws CandidateNotExistsException {
        doNothing().when(candidateServiceImp).deleteCandidate(1L);
        ResponseEntity<ResponseMessage> response = candidateController.deleteCandidate(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(candidateServiceImp, times(1)).deleteCandidate(1L);
    }

}


