package com.testbackend.test.controller;

import com.testbackend.test.exception.TechnologyAlreadyExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.model.entity.Technology;
import com.testbackend.test.model.util.ResponseMessage;
import com.testbackend.test.repository.TechnologyRepository;
import com.testbackend.test.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.testbackend.test.util.UrlBuilder.buildURL;
import static com.testbackend.test.util.ResponseUtil.messageResponse;

@RestController
@RequestMapping("/technologies")
public class TechnologyController {

    @Autowired
    TechnologyService technologyService;

    @Autowired
    TechnologyRepository technologyRepository;

    @GetMapping
    public ResponseEntity<List<TechnologyDto>> getAllTechnologies(){
        return new ResponseEntity<>(technologyService.getAllTechnologies(), HttpStatus.OK);
    }

    @GetMapping("/{idTechnology}")
    public ResponseEntity<TechnologyDto> getTechnologyById(@PathVariable Long idTechnology) throws TechnologyNotExistsException {
        return ResponseEntity.ok(technologyService.getTechnologyDtoById(idTechnology));
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> addTechnology(@Valid @RequestBody TechnologyDto technologyDto) throws TechnologyAlreadyExistsException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(buildURL("technologies", technologyService.addTechnology(technologyDto).getIdTechnology()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse("Technology has been created"));
    }

    @PutMapping("/{idTechnology}")
    public ResponseEntity<Object> updateTechnology(@Valid @RequestBody Technology technology, @PathVariable Long idTechnology) throws TechnologyNotExistsException {
        Optional<Technology> techonologyOptional = technologyRepository.findById(idTechnology);
        if(techonologyOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            technology.setIdTechnology(idTechnology);
            technologyRepository.save(technology);
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{idTechnology}")
    public ResponseEntity<String> deleteTechnology(@PathVariable Long idTechnology) throws TechnologyNotExistsException{
        technologyService.deleteTechnology(idTechnology);
        return ResponseEntity.ok().body("Technology Deleted");
    }
}
