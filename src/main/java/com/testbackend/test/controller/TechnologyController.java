package com.testbackend.test.controller;

import com.testbackend.test.exception.TechnologyAlreadyExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.CandidateByTechnologyDto;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.dto.TechnologyDto;
import com.testbackend.test.model.util.ResponseMessage;
import com.testbackend.test.projection.CandidateByTechnologyProjection;
import com.testbackend.test.service.CandidateByTechnologyService;
import com.testbackend.test.service.TechnologyService;
import com.testbackend.test.service.imp.CandidateByTechnologyServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Operation(
            summary = "Find and get all technologies",
            description = "get a list of all technologies")
    @GetMapping
    public ResponseEntity<List<TechnologyDto>> getAllTechnologies() {
        return new ResponseEntity<>(technologyService.getAllTechnologies(), HttpStatus.OK);
    }

    @Operation(
            summary = "Find and get a Technology",
            description = "Get a Technology by Id")
    @GetMapping("/{idTechnology}")
    public ResponseEntity<TechnologyDto> getTechnologyById(@PathVariable Long idTechnology) {
        return ResponseEntity.ok(technologyService.getTechnologyDtoById(idTechnology));
    }

    @Operation(
            summary = "Create a new Technology",
            description = "Create a new Technology")
    @PostMapping
    public ResponseEntity<String> addTechnology(@Valid @RequestBody TechnologyDto technologyDto) {
        technologyService.addTechnology(technologyDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update a Technology",
            description = "Update technology information")
    @PutMapping
    public ResponseEntity<String> updateTechnology(TechnologyDto technologyDto, Long id) {
        technologyService.updateTechnology(technologyDto,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a Technology",
            description = "Delete a Technology")
    @DeleteMapping("/{idTechnology}")
    public ResponseEntity<String> deleteTechnology(@PathVariable Long idTechnology) {
        technologyService.deleteTechnology(idTechnology);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
