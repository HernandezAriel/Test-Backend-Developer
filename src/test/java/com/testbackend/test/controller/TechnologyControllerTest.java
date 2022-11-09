package com.testbackend.test.controller;

import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.service.imp.TechnologyServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.testbackend.test.testUtil.TechnologyTestUtil.getListTechnologyDto;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnologyDto;

public class TechnologyControllerTest {

    private TechnologyServiceImp technologyServiceImp;
    private TechnologyController technologyController;

    @Before
    public void setUp() {
        technologyServiceImp = mock(TechnologyServiceImp.class);
        technologyController = new TechnologyController(technologyServiceImp);
    }

    @Test
    public void addTechnologyTest() {
        TechnologyDto technologyDto = getTechnologyDto();
        TechnologyDto technologyCreateUpdate = getTechnologyDto();
        when(technologyServiceImp.addTechnology(technologyCreateUpdate)).thenReturn(technologyDto);
        ResponseEntity<String> response = technologyController.addTechnology(technologyDto);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(technologyServiceImp, times(1)).addTechnology(technologyDto);
    }

    @Test
    public void getAllTechnologiesTest() {
        List<TechnologyDto> technologies = getListTechnologyDto();
        when(technologyServiceImp.getAllTechnologies()).thenReturn(technologies);
        ResponseEntity<List<TechnologyDto>> response = technologyController.getAllTechnologies();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(technologyServiceImp, times(1)).getAllTechnologies();
    }

    @Test
    public void getTechnologyByIdOkTest() throws CandidateNotExistsException {
        when(technologyServiceImp.getTechnologyDtoById(1L)).thenReturn(getTechnologyDto());
        ResponseEntity<TechnologyDto> response = technologyController.getTechnologyById(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(getTechnologyDto(), response.getBody());
        verify(technologyServiceImp, times(1)).getTechnologyDtoById(1L);
    }

    @Test
    public void deleteTechnologyOkTest() throws TechnologyNotExistsException {
        doNothing().when(technologyServiceImp).deleteTechnology(1L);
        ResponseEntity<String> response = technologyController.deleteTechnology(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(technologyServiceImp, times(1)).deleteTechnology(1L);
    }
}