package com.testbackend.test.service.imp;

import com.testbackend.test.exception.EmptyException;
import com.testbackend.test.exception.TechnologyAlreadyExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.model.entity.CandidateByTechnology;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.projection.CandidateByTechnologyProjection;
import com.testbackend.test.repository.TechnologyRepository;
import com.testbackend.test.service.CandidateByTechnologyService;
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
    private final ModelMapper modelMapper;

    @Autowired
    public TechnologyServiceImp(TechnologyRepository technologyRepository, ModelMapper modelMapper) {
        this.technologyRepository = technologyRepository;
        this.modelMapper = modelMapper;
    }

    public TechnologyDto addTechnology(TechnologyDto technologyDto) {
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

    public Technology getTechnologyById(Long idTechnology) {
        return technologyRepository.findById(idTechnology)
                .orElseThrow(() -> new TechnologyNotExistsException("Technology not exists"));
    }

    public TechnologyDto getTechnologyDtoById(Long idTechnology) {
        Technology technology = getTechnologyById(idTechnology);
        return modelMapper.map(technology, TechnologyDto.class);
    }

    public void updateTechnology(TechnologyDto technologyDto, Long id) {
        technologyRepository.save(modelMapper.map(technologyDto, getTechnologyById(id).getClass()));
    }

    public void deleteTechnology(Long idTechnology) {
        technologyRepository.deleteById(getTechnologyById(idTechnology).getIdTechnology());
        log.info("Delete Successful");
    }
}
