package com.testbackend.test.controller;

import com.testbackend.test.exception.TechnologyAlreadyExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.model.util.ResponseMessage;
import com.testbackend.test.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.testbackend.test.util.UrlBuilder.buildURL;
import static com.testbackend.test.util.ResponseUtil.messageResponse;

@RestController
@RequestMapping("/technologies")
public class TechnologyController {

    private final TechnologyService technologyService;

    @Autowired
    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping
    public ResponseEntity<List<TechnologyDto>> getAllTechnologies() {
        return new ResponseEntity<>(technologyService.getAllTechnologies(), HttpStatus.OK);
    }

    @GetMapping("/{idTechnology}")
    public ResponseEntity<TechnologyDto> getTechnologyById(@PathVariable Long idTechnology) {
        return ResponseEntity.ok(technologyService.getTechnologyDtoById(idTechnology));
    }

    @PostMapping
    public ResponseEntity<String> addTechnology(@Valid @RequestBody TechnologyDto technologyDto) {
        technologyService.addTechnology(technologyDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{idTechnology}")
    public ResponseEntity<String> deleteTechnology(@PathVariable Long idTechnology) {
        technologyService.deleteTechnology(idTechnology);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
