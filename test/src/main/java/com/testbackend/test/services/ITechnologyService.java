package com.testbackend.test.services;

import com.testbackend.test.models.dtos.TechnologyDto;
import com.testbackend.test.models.entities.Technology;

import java.util.List;

public interface ITechnologyService {

    Technology addTechnology(Technology technology);
    List<TechnologyDto> getAllTechnologies();
    Technology getTechnologyById(Long idTechnology);
    TechnologyDto getTechnologyDtoById(Long idTechnology);
    Technology updateTechnology(Technology technology);
    void deleteTechnology(Long idTechnology);
}
