package com.testbackend.test.service;

import com.testbackend.test.exception.TechnologyAlreadyExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.model.entity.Technology;

import java.util.List;

public interface TechnologyService {

    TechnologyDto addTechnology(TechnologyDto technologyDto) throws TechnologyAlreadyExistsException;
    List<TechnologyDto> getAllTechnologies();
    Technology getTechnologyById(Long idTechnology) throws TechnologyNotExistsException;
    TechnologyDto getTechnologyDtoById(Long idTechnology) throws TechnologyNotExistsException;
    Technology updateTechnology(Technology technology) throws TechnologyNotExistsException;
    void deleteTechnology(Long idTechnology) throws TechnologyNotExistsException;
}

