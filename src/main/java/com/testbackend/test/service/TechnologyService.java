package com.testbackend.test.service;

import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.model.entity.Technology;

import java.util.List;

public interface TechnologyService {

    TechnologyDto addTechnology(TechnologyDto technologyDto);

    List<TechnologyDto> getAllTechnologies();

    Technology getTechnologyById(Long idTechnology);

    TechnologyDto getTechnologyDtoById(Long idTechnology);

    void updateTechnology(TechnologyDto technologyDto, Long id);

    void deleteTechnology(Long idTechnology);
}

