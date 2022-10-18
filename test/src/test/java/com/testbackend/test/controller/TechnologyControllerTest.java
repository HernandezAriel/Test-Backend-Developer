package com.testbackend.test.controller;

import com.testbackend.test.exception.TechnologyAlreadyExistsException;
import com.testbackend.test.model.util.ResponseMessage;
import com.testbackend.test.service.imp.TechnologyServiceImp;
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
import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidateDto;
import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnology;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getListExperienceDto;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getCandidateByTechnology;
import static com.testbackend.test.testUtil.CandidateByTechnologyTestUtil.getListCandidateByTechnology;

public class TechnologyControllerTest {

    private TechnologyServiceImp technologyServiceImp;
    private TechnologyController technologyController;

    @Before
    public void start() {
        technologyServiceImp = mock(TechnologyServiceImp.class);
        technologyController = new TechnologyController(technologyServiceImp);
    }

    @Test
    public void addTechnologyOkTest() throws TechnologyAlreadyExistsException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(technologyServiceImp.addTechnology(getTechnology())).thenReturn(getTechnology());
        ResponseEntity<ResponseMessage> response = technologyController.addTechnology(getTechnology());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(UrlBuilder.buildURL("technologies", getTechnology().getIdTechnology()).toString()
                , Objects.requireNonNull(((ResponseEntity<?>) response).getHeaders().get("Location")).get(0));
        verify(technologyServiceImp, times(1)).addTechnology(getTechnology());
    }
}
