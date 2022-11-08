package com.testbackend.test.controller;

import com.testbackend.test.exception.CandidateAlreadyExistsException;
import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.util.ResponseMessage;
import com.testbackend.test.service.CandidateService;
import com.testbackend.test.service.imp.CandidateServiceImp;
import io.swagger.annotations.Tag;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static com.testbackend.test.util.UrlBuilder.buildURL;
import static com.testbackend.test.util.ResponseUtil.messageResponse;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<List<CandidateDto>> getAllCandidates() {
        return new ResponseEntity<>(candidateService.getAllCandidates(), HttpStatus.OK);
    }

    @GetMapping("/{idCandidate}")
    public ResponseEntity<CandidateDto> getCandidateById(@PathVariable Long idCandidate) {
        return ResponseEntity.ok(candidateService.getCandidateDtoById(idCandidate));
    }

    @PostMapping
    public ResponseEntity<String> addCandidate(@Valid @RequestBody CandidateDto candidateDto) {
        candidateService.addCandidate(candidateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idCandidate}")
    public ResponseEntity<String> updateCandidate(@Valid @RequestBody CandidateDto candidateDto, @PathVariable Long idCandidate) {
        candidateService.updateCandidate(candidateDto, idCandidate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{idCandidate}/technologies/{idTechnology}/{experience}")
    public ResponseEntity<String> addTechnologyToCandidate(@Valid @PathVariable Long idCandidate, @PathVariable Long idTechnology, @PathVariable Long experience) {
        candidateService.addCandidateByTechnology(idCandidate, idTechnology, experience);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{idCandidate}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long idCandidate) {
        candidateService.deleteCandidate(idCandidate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
