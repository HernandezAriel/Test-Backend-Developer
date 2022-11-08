package com.testbackend.test.controller;

import com.testbackend.test.projection.CandidateByTechnologyProjection;
import com.testbackend.test.service.CandidateByTechnologyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/candidateByTechnology")
public class CandidateByTechnologyController {

    private final CandidateByTechnologyService candidateByTechnologyService;

    @Autowired
    public CandidateByTechnologyController(CandidateByTechnologyService candidateByTechnologyService) {
        this.candidateByTechnologyService = candidateByTechnologyService;
    }

    @Operation(
            summary = "Find and get all Candidates by technology",
            description = "get a list of all Candidates with a specific technology")
    @GetMapping("/{name}")
    public ResponseEntity<List<CandidateByTechnologyProjection>> findCandidatesByTechnologies(@PathVariable String name) {
        return new ResponseEntity<>(candidateByTechnologyService.getCandidatesByTechnologyByNameTechnology(name), HttpStatus.OK);
    }

}
