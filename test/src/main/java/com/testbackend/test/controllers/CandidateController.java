package com.testbackend.test.controllers;

import com.testbackend.test.models.dtos.CandidateDto;
import com.testbackend.test.services.Imp.CandidateService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @GetMapping
    public ResponseEntity<List<CandidateDto>> getAllCandidates(){
        return new ResponseEntity<>(candidateService.getAllCandidates(), HttpStatus.OK);
    }

    @GetMapping("/{idCandidate}")
    public ResponseEntity<CandidateDto> getCandidateById(@PathVariable Long idCandidate){
        return ResponseEntity.ok(candidateService.getCandidateDtoById(idCandidate));
    }

}
