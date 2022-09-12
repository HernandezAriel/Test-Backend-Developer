package com.testbackend.test.controllers;

import com.testbackend.test.models.dtos.TechnologyDto;
import com.testbackend.test.models.entities.Technology;
import com.testbackend.test.services.ITechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<TechnologyDto> getTechnologyById(@PathVariable Long idTechnology){
        return ResponseEntity.ok(technologyService.getTechnologyDtoById(idTechnology));
    }

    @PostMapping
    public ResponseEntity addTechnology(@RequestBody Technology technology){
        technologyService.addTechnology(technology);
        return ResponseEntity.ok().body("Success. ");
    }

    @DeleteMapping("/{idTechnology}")
    public ResponseEntity<String> deleteTechnology(@PathVariable Long idTechnology){
        technologyService.deleteTechnology(idTechnology);
        return ResponseEntity.ok().body("Technology Deleted");
    }
}
