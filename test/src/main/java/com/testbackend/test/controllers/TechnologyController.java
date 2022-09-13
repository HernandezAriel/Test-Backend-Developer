package com.testbackend.test.controllers;

import com.testbackend.test.exceptions.TechnologyAlreadyExistsException;
import com.testbackend.test.exceptions.TechnologyNotExistsException;
import com.testbackend.test.models.dtos.TechnologyDto;
import com.testbackend.test.models.entities.Technology;
import com.testbackend.test.models.utils.ResponseMessage;
import com.testbackend.test.services.ITechnologyService;
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

import static com.testbackend.test.utils.UrlBuilder.buildURL;
import static com.testbackend.test.utils.ResponseUtil.messageResponse;

@RestController
@RequestMapping("/technologies")
public class TechnologyController {

    @Autowired
    ITechnologyService technologyService;

    @GetMapping
    public ResponseEntity<List<TechnologyDto>> getAllTechnologies(){
        return new ResponseEntity<>(technologyService.getAllTechnologies(), HttpStatus.OK);
    }

    @GetMapping("/{idTechnology}")
    public ResponseEntity<TechnologyDto> getTechnologyById(@PathVariable Long idTechnology) throws TechnologyNotExistsException {
        return ResponseEntity.ok(technologyService.getTechnologyDtoById(idTechnology));
    }

    @PostMapping
    public ResponseEntity addTechnology(@Valid  @RequestBody Technology technology) throws TechnologyAlreadyExistsException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(buildURL("technologies", technologyService.addTechnology(technology).getIdTechnology()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse("Technology has been created"));
    }

    @PutMapping("/{idTechnology}")
    public ResponseEntity<ResponseMessage> updateTechnology(@Valid @RequestBody Technology technology) throws TechnologyNotExistsException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .location(buildURL("technologies", technologyService.updateTechnology(technology).getIdTechnology()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse("Technology has been updated"));
    }

    @DeleteMapping("/{idTechnology}")
    public ResponseEntity<String> deleteTechnology(@PathVariable Long idTechnology) throws TechnologyNotExistsException{
        technologyService.deleteTechnology(idTechnology);
        return ResponseEntity.ok().body("Technology Deleted");
    }
}