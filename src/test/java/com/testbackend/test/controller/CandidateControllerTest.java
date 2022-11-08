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

import java.util.Objects;
import java.util.Set;

import static com.testbackend.test.testUtil.CandidateTestUtil.getCandidateDtoUpdate;
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

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        candidateService = mock(CandidateService.class);
        candidateController = new CandidateController(candidateService);
    }

    @Test
    public void createCandidateTest() throws Exception {
        CandidateDto candidateDto = getCandidateDto();
        CandidateDto candidateCreateUpdate = getCandidateDtoUpdate();
        when(candidateService.addCandidate(candidateCreateUpdate)).thenReturn(candidateDto);
        String candidateGson = new Gson().toJson(candidateDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/candidates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(candidateGson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
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
    public void addTechnologyToCandidateOkTest() throws TechnologyNotExistsException, CandidateByTechnologyAlreadyExistsException, CandidateNotExistsException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(candidateService.addCandidateByTechnology(1L, 1L, 1L)).thenReturn(getCandidate());
        ResponseEntity<String> response = candidateController.addCandidateByTechnology(1L, 1L, 1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(UrlBuilder.buildURL("candidates", getCandidate().getIdCandidate()).toString()
                , Objects.requireNonNull(response.getHeaders().get("Location")).get(0));
        verify(candidateService, times(1)).addCandidateByTechnology(1L, 1L, 1L);
    }

    @Test
    public void deleteCandidateOkTest() throws CandidateNotExistsException {
        doNothing().when(candidateService).deleteCandidate(1L);
        ResponseEntity<String> response = candidateController.deleteCandidate(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(candidateService, times(1)).deleteCandidate(1L);
    }

}


