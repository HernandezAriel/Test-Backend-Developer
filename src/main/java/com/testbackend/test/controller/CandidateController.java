package com.testbackend.test.controller;

import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.service.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Operation(
            summary = "Find and get all Candidates",
            description = "get a list of all Candidates")
    @GetMapping
    public ResponseEntity<List<CandidateDto>> getAllCandidates() {
        return new ResponseEntity<>(candidateService.getAllCandidates(), HttpStatus.OK);
    }

    @Operation(
            summary = "Find and get a Candidate",
            description = "Get a Candidate by Id")
    @GetMapping("/{idCandidate}")
    public ResponseEntity<CandidateDto> getCandidateById(@PathVariable Long idCandidate) {
        return ResponseEntity.ok(candidateService.getCandidateDtoById(idCandidate));
    }

    @Operation(
            summary = "Create a new Candidate",
            description = "Create a new Candidate")
    @PostMapping
    public ResponseEntity<String> addCandidate(@Valid @RequestBody CandidateDto candidateDto) {
        candidateService.addCandidate(candidateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update a Candidate",
            description = "Update candidate information")
    @PutMapping("/{idCandidate}")
    public ResponseEntity<String> updateCandidate(@Valid @RequestBody CandidateDto candidateDto, @PathVariable Long idCandidate) {
        candidateService.updateCandidate(candidateDto, idCandidate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Add Technology To Candidate",
            description = "Add a Technology to candidate")
    @PutMapping("/{idCandidate}/technologies/{idTechnology}/{experience}")
    public ResponseEntity<String> addCandidateByTechnology(@Valid @PathVariable Long idCandidate, @PathVariable Long idTechnology, @PathVariable Long experience) {
        candidateService.addCandidateByTechnology(idCandidate, idTechnology, experience);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Delete a Candidate",
            description = "Delete a candidate")
    @DeleteMapping("/{idCandidate}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long idCandidate) {
        candidateService.deleteCandidate(idCandidate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
