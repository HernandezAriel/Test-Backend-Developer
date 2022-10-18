package com.testbackend.test.service.Imp;

import com.testbackend.test.exception.TechnologyAlreadyExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.repository.TechnologyRepository;
import com.testbackend.test.service.TechnologyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class TechnologyServiceImp implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;
    @Autowired
    private CandidateByTechnologyServiceImp candidateByTechnologyServiceImp;
    @Autowired
    ModelMapper modelMapper;

    public Technology addTechnology(Technology technology) throws TechnologyAlreadyExistsException {
        log.debug("Technology to add: " + technology);
        if(!isNull(technologyRepository.findByNameAndVersion(technology.getName(), technology.getVersion()))){
            log.error("Technology already exists");
            throw new TechnologyAlreadyExistsException("Technology " + technology.getName() + " version " + technology.getVersion() + " already exists");
        }
        else {
            log.info("Candidate has been created");
            return technologyRepository.save(technology);
        }
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
        if(technologyRepository.findByName(technology.getName()) == null){
            log.error("Technology not exists");
            throw new TechnologyNotExistsException("Technology " + technology.getName() + " not exists");
        }
        else{
            log.info("Candidate updated");
            return technologyRepository.save(technology);
        }

    }

    public void deleteTechnology(Long idTechnology) throws TechnologyNotExistsException{
        log.info("idTechnology" + idTechnology);
        Technology technology = getTechnologyById(idTechnology);
        log.debug("Technology to delete: " + technology);
        if((candidateByTechnologyServiceImp.getCandidatesByTechnologyByTechnology(technology))==null){
            log.error("Technology not exists");
            throw new TechnologyNotExistsException("Technology " + technology.getName() + " not exists");
        }
        else{
            log.info("Candidate deleted");
            technologyRepository.delete(technology);
        }
    }
}
