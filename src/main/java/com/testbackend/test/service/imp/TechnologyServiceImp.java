package com.testbackend.test.service.imp;

import com.testbackend.test.exception.EmptyException;
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


@Slf4j
@Service
public class TechnologyServiceImp implements TechnologyService {

    private final TechnologyRepository technologyRepository;
    private final CandidateByTechnologyServiceImp candidateByTechnologyServiceImp;
    private final ModelMapper modelMapper;

    @Autowired
    public TechnologyServiceImp(TechnologyRepository technologyRepository, CandidateByTechnologyServiceImp candidateByTechnologyServiceImp, ModelMapper modelMapper) {
        this.technologyRepository = technologyRepository;
        this.candidateByTechnologyServiceImp = candidateByTechnologyServiceImp;
        this.modelMapper = modelMapper;
    }

    public TechnologyDto addTechnology(TechnologyDto technologyDto) throws TechnologyAlreadyExistsException {
        log.debug("Technology to add: " + technologyDto);
        if (technologyRepository.findByNameAndVersion(technologyDto.getName(), technologyDto.getVersion()).isPresent()) {
            throw new TechnologyAlreadyExistsException("Technology already exists");
        } else {
            technologyRepository.save(modelMapper.map(technologyDto, Technology.class));
            log.info("Candidate has been created");
            return technologyDto;
        }
    }

    public List<TechnologyDto> getAllTechnologies() {
        List<Technology> technologies = technologyRepository.findAll();
        List<TechnologyDto> technologiesDto = new ArrayList<>();
        if (technologies.isEmpty()) {
            throw new EmptyException("Techonology List is empty");
        }
        for (Technology technology : technologies) {
            technologiesDto.add(modelMapper.map(technology, TechnologyDto.class));
        }
        return technologiesDto;
    }

    public Technology getTechnologyById(Long idTechnology) throws TechnologyNotExistsException {
        return technologyRepository.findById(idTechnology).orElseThrow(() -> new TechnologyNotExistsException("Technology not exists"));
    }

    public TechnologyDto getTechnologyDtoById(Long idTechnology) throws TechnologyNotExistsException {
        Technology technology = technologyRepository.findById(idTechnology).orElse(null);
        return modelMapper.map(technology, TechnologyDto.class);
    }

    public Technology updateTechnology(Technology technology) throws TechnologyNotExistsException {
        if (technologyRepository.findByName(technology.getName()) == null) {
            log.error("Technology not exists");
            throw new TechnologyNotExistsException("Technology " + technology.getName() + " not exists");
        } else {
            log.info("Candidate updated");
            return technologyRepository.save(technology);
        }
    }

    public void deleteTechnology(Long idTechnology) throws TechnologyNotExistsException {
        log.info("idTechnology" + idTechnology);
        Technology technology = getTechnologyById(idTechnology);
        log.debug("Technology to delete: " + technology);
        if ((candidateByTechnologyServiceImp.getCandidatesByTechnologyByTechnology(technology)) == null) {
            log.error("Technology not exists");
            throw new TechnologyNotExistsException("Technology " + technology.getName() + " not exists");
        } else {
            log.info("Candidate deleted");
            technologyRepository.delete(technology);
        }
    }
}
