package com.testbackend.test.controller;

import com.testbackend.test.exception.CandidateAlreadyExistsException;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidate;
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
        when(candidateServiceImp.addCandidate(getCandidate())).thenReturn(getCandidate());
        ResponseEntity<ResponseMessage> response = candidateController.addCandidate(getCandidate());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(UrlBuilder.buildURL("candidates", getCandidate().getIdCandidate()).toString()
                , Objects.requireNonNull(response.getHeaders().get("Location")).get(0));
        verify(candidateServiceImp, times(1)).addCandidate(getCandidate());
    }
}


