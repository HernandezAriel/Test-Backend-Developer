package com.testbackend.test.services.Imp;

import com.testbackend.test.exceptions.TechnologyAlreadyExistsException;
import com.testbackend.test.exceptions.TechnologyNotExistsException;
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
    @Autowired
    ModelMapper modelMapper;

    public Technology addTechnology(Technology technology) throws TechnologyAlreadyExistsException {
        if((technologyRepository.findByNameAndVersion(technology.getName(), technology.getVersion())) != null)
            throw new TechnologyAlreadyExistsException("Technology " + technology.getName() + " version " + technology.getVersion() + " already exists");
        else
            return technologyRepository.save(technology);
    }

    public List<TechnologyDto> getAllTechnologies(){
        List<TechnologyDto> technologiesDto = new ArrayList<>();
        for(Technology technology: technologyRepository.findAll()){
            technologiesDto.add(modelMapper.map(technology,TechnologyDto.class));
        }
        return technologiesDto;
    }

    public Technology getTechnologyById(Long idTechnology) throws TechnologyNotExistsException {
        return technologyRepository.findById(idTechnology).orElseThrow(() -> new TechnologyNotExistsException("Technology not exists") );
    }

    public TechnologyDto getTechnologyDtoById(Long idTechnology) throws TechnologyNotExistsException{
        Technology technology = technologyRepository.findById(idTechnology).orElse(null);
        return modelMapper.map(technology, TechnologyDto.class);
    }

    public Technology updateTechnology(Technology technology) throws TechnologyNotExistsException{
        if(technologyRepository.findByName(technology.getName()) == null)
            throw new TechnologyNotExistsException("Technology " + technology.getName() + " not exists");
        else
            return technologyRepository.save(technology);
    }

    public void deleteTechnology(Long idTechnology) throws TechnologyNotExistsException{
        Technology technology = getTechnologyById(idTechnology);
        if((candidateByTechnologyService.getCandidatesByTechnologyByTechnology(technology))!=null)
            technologyRepository.deleteById(idTechnology);
    }
}
