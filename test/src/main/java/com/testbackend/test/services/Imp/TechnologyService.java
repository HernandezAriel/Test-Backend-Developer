package com.testbackend.test.services.Imp;

import com.testbackend.test.models.dtos.TechnologyDto;
import com.testbackend.test.models.entities.Technology;
import com.testbackend.test.repositories.TechnologyRepository;
import com.testbackend.test.services.ITechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyService implements ITechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;
    @Autowired
    private CandidateByTechnologyService candidateByTechnologyService;
    private final ModelMapper modelMapper;

    public TechnologyService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Technology addTechnology(Technology technology){
        return technologyRepository.save(technology);
    }

    public List<TechnologyDto> getAllTechnologies(){
        List<TechnologyDto> technologiesDto = new ArrayList<>();
        for(Technology technology: technologyRepository.findAll()){
            technologiesDto.add(modelMapper.map(technology,TechnologyDto.class));
        }
        return technologiesDto;
    }

    public Technology getTechnologyById(Long idTechnology){
        return technologyRepository.findById(idTechnology).orElse(null);
    }

    public TechnologyDto getTechnologyDtoById(Long idTechnology){
        Technology technology = technologyRepository.findById(idTechnology).orElse(null);
        return modelMapper.map(technology, TechnologyDto.class);
    }

    public Technology updateTechnology(Technology technology){
        return technologyRepository.save(technology);
    }

    public void deleteTechnology(Long idTechnology){
        Technology technology = getTechnologyById(idTechnology);
        if((candidateByTechnologyService.getCandidatesByTechnologyByTechnology(technology))==null)
            technologyRepository.deleteById(idTechnology);
    }
}
