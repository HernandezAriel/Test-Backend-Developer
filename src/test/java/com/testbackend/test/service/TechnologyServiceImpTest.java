package com.testbackend.test.service;

import com.testbackend.test.exception.TechnologyAlreadyExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.repository.TechnologyRepository;
import com.testbackend.test.service.imp.TechnologyServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static com.testbackend.test.testUtil.TechnologyTestUtil.getListTechnology;
import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnologyDto;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import static com.testbackend.test.testUtil.TechnologyTestUtil.getTechnology;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TechnologyServiceImpTest {

    private TechnologyRepository technologyRepository;
    private TechnologyServiceImp technologyServiceImp;

    @Before
    public void start() {
        technologyRepository = mock(TechnologyRepository.class);
        ModelMapper modelMapper = mock(ModelMapper.class);
        technologyServiceImp = new TechnologyServiceImp(technologyRepository, modelMapper);
    }

    @Test
    public void addTechnologyOkTest() throws TechnologyAlreadyExistsException {
        Technology technology = getTechnology();
        when(technologyRepository.save(technology)).thenReturn(technology);
        TechnologyDto technologyDto = technologyServiceImp.addTechnology(getTechnologyDto());
        assertEquals(technologyDto, technologyServiceImp.addTechnology(getTechnologyDto()));
    }

    @Test
    public void addTechnologyAlreadyExists() {
        when(technologyRepository.findByNameAndVersion("Java", "11")).thenReturn(Optional.ofNullable(getTechnology()));
        Assert.assertThrows(TechnologyAlreadyExistsException.class, () -> technologyServiceImp.addTechnology(getTechnologyDto()));
        verify(technologyRepository, times(1)).findByNameAndVersion("Java", "11");
        verify(technologyRepository, times(0)).save(getTechnology());
    }

    @Test
    public void getAllTechnologiesTest() {
        List<Technology> technologies = getListTechnology();
        when(technologyRepository.findAll()).thenReturn(technologies);
        List<TechnologyDto> candidatesDto = technologyServiceImp.getAllTechnologies();
        verify(technologyRepository, times(1)).findAll();
        Assertions.assertEquals(candidatesDto, technologyServiceImp.getAllTechnologies());
    }

    @Test
    public void getTechnologyByIdOkTest() throws TechnologyNotExistsException {
        when(technologyRepository.findById(1L)).thenReturn(Optional.of(getTechnology()));
        Technology technology = technologyServiceImp.getTechnologyById(1L);
        verify(technologyRepository, times(1)).findById(1L);
        assertEquals(technology, technologyServiceImp.getTechnologyById(1L));

    }

    @Test
    public void getTechnologyByIdNotExistsTest() {
        when(technologyRepository.findById(1L)).thenReturn(Optional.empty());
        Assert.assertThrows(TechnologyNotExistsException.class, () -> technologyServiceImp.getTechnologyById(1L));
        verify(technologyRepository, times(1)).findById(1L);
    }

    @Test
    public void getTechnologyDtoByIdOkTest() {
        when(technologyRepository.findById(1L)).thenReturn(Optional.of(getTechnology()));
        TechnologyDto technologyDto = technologyServiceImp.getTechnologyDtoById(1L);
        verify(technologyRepository, times(1)).findById(1L);
        assertEquals(technologyDto, technologyServiceImp.getTechnologyDtoById(1L));
    }

    @Test
    public void deleteTechnologyOkTest() throws TechnologyNotExistsException {
        when(technologyRepository.findById(1L)).thenReturn(Optional.of(getTechnology()));
        doNothing().when(technologyRepository).deleteById(1L);
        technologyServiceImp.deleteTechnology(1L);
        verify(technologyRepository, times(1)).findById(1L);
        verify(technologyRepository, times(1)).deleteById(1L);
    }
}
