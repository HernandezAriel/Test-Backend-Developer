package com.testbackend.test.service;

import com.testbackend.test.exception.TechnologyAlreadyExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.repository.TechnologyRepository;
import com.testbackend.test.service.imp.CandidateByTechnologyServiceImp;
import com.testbackend.test.service.imp.TechnologyServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnologyDto;
import static org.mockito.Mockito.doNothing;
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
        Technology technology = technologyServiceImp.addTechnology(getTechnologyDto());
        Assertions.assertEquals(getTechnology(), technology);
        verify(technologyRepository, times(1)).findByNameAndVersion("Java", "11");
        verify(technologyRepository, times(1)).save(getTechnology());
    }

    @Test
    public void addTechnologyAlreadyExists() {
        when(technologyRepository.findByNameAndVersion("Java", "11")).thenReturn(getTechnology());
        Assert.assertThrows(TechnologyAlreadyExistsException.class, () -> technologyServiceImp.addTechnology(getTechnologyDto()));
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

    @Test
    public void getTechnologyByIdNotExistsTest() {
        when(technologyRepository.findById(1L)).thenReturn(Optional.empty());
        Assert.assertThrows(TechnologyNotExistsException.class, () -> technologyServiceImp.getTechnologyById(1L));
        verify(technologyRepository, times(1)).findById(1L);
    }

    @Test
    public void deleteTechnologyOkTest() throws TechnologyNotExistsException {
        when(technologyRepository.findById(1L)).thenReturn(Optional.of(getTechnology()));
        when(candidateByTechnologyServiceImp.getCandidatesByTechnologyByTechnology(getTechnology())).thenReturn(List.of());
        doNothing().when(technologyRepository).deleteById(1L);
        technologyServiceImp.deleteTechnology(1L);
        verify(technologyRepository, times(1)).findById(1L);
        verify(candidateByTechnologyServiceImp, times(1)).getCandidatesByTechnologyByTechnology(getTechnology());
        verify(technologyRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteTechnologyNotExistsTest() {
        when(technologyRepository.findById(1L)).thenReturn(Optional.empty());
        Assert.assertThrows(TechnologyNotExistsException.class, () -> technologyServiceImp.deleteTechnology(1L));
        verify(technologyRepository, times(1)).findById(1L);
        verify(candidateByTechnologyServiceImp, times(0)).getCandidatesByTechnologyByTechnology(getTechnology());
        verify(technologyRepository, times(0)).deleteById(1L);
    }

}
