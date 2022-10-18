package com.testbackend.test.service;

import com.testbackend.test.exception.TechnologyAlreadyExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.repository.TechnologyRepository;
import com.testbackend.test.service.imp.CandidateByTechnologyServiceImp;
import com.testbackend.test.service.imp.TechnologyServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.Mockito.mock;

import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnology;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TechnologyServiceImpTest {

    private TechnologyRepository technologyRepository;
    private CandidateByTechnologyServiceImp candidateByTechnologyServiceImp;
    private ModelMapper modelMapper;
    private TechnologyServiceImp technologyServiceImp;

    @Before
    public void start() {
        technologyRepository = mock(TechnologyRepository.class);
        candidateByTechnologyServiceImp = mock(CandidateByTechnologyServiceImp.class);
        modelMapper = mock(ModelMapper.class);
        technologyServiceImp = new TechnologyServiceImp(technologyRepository, candidateByTechnologyServiceImp, modelMapper);
    }

    @Test
    public void addTechnologyOkTest() throws TechnologyAlreadyExistsException {
        when(technologyRepository.findByNameAndVersion("Java", "11")).thenReturn(null);
        when(technologyRepository.save(getTechnology())).thenReturn(getTechnology());
        Technology technology = technologyServiceImp.addTechnology(getTechnology());
        Assertions.assertEquals(getTechnology(), technology);
        verify(technologyRepository, times(1)).findByNameAndVersion("Java", "11");
        verify(technologyRepository, times(1)).save(getTechnology());
    }

    @Test
    public void addTechnologyAlreadyExists() {
        when(technologyRepository.findByNameAndVersion("Java", "11")).thenReturn(getTechnology());
        Assert.assertThrows(TechnologyAlreadyExistsException.class, () -> technologyServiceImp.addTechnology(getTechnology()));
        verify(technologyRepository, times(1)).findByNameAndVersion("Java", "11");
        verify(technologyRepository, times(0)).save(getTechnology());
    }

    @Test
    public void getTechnologyByIdOkTest() throws TechnologyNotExistsException {
        when(technologyRepository.findById(1L)).thenReturn(Optional.of(getTechnology()));
        Technology technology = technologyServiceImp.getTechnologyById(1L);
        Assertions.assertEquals(getTechnology(), technology);
        verify(technologyRepository, times(1)).findById(1L);
    }

}
