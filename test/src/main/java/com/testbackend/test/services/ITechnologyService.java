package com.testbackend.test.services;

import com.testbackend.test.exceptions.TechnologyAlreadyExistsException;
import com.testbackend.test.exceptions.TechnologyNotExistsException;
import com.testbackend.test.models.dtos.TechnologyDto;
import com.testbackend.test.models.entities.Technology;

import java.util.List;

public interface ITechnologyService {

    Technology addTechnology(Technology technology) throws TechnologyAlreadyExistsException;
    List<TechnologyDto> getAllTechnologies();
    Technology getTechnologyById(Long idTechnology) throws TechnologyNotExistsException;
    TechnologyDto getTechnologyDtoById(Long idTechnology) throws TechnologyNotExistsException;
    Technology updateTechnology(Technology technology) throws TechnologyNotExistsException;
    void deleteTechnology(Long idTechnology) throws TechnologyNotExistsException;
}
