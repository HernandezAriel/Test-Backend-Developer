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
    public CandidateController(CandidateServiceImp candidateServiceImp) {
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

//    @GetMapping("/technologies/{nameTechnology}")
//    public ResponseEntity<Set<CandidateDto>> getCandidatesByTechnology(@PathVariable String nameTechnology) {
//        return ResponseEntity.ok(candidateServiceImp.getCandidatesByTechnology(nameTechnology));
//    }

    @PostMapping
    public ResponseEntity<String> addCandidate(@Valid @RequestBody CandidateDto candidateDto) {
        candidateService.addCandidate(candidateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idCandidate}/technologies/{idTechnology}")
    public ResponseEntity<ResponseMessage> addTechnologyToCandidate(@Valid @PathVariable Long idCandidate, @PathVariable Long idTechnology, @RequestParam Long experience) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .location(buildURL("candidates", candidateServiceImp.addTechnologyToCandidate(idCandidate, idTechnology, experience).getIdCandidate()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse("Technology added to candidate"));
    }

    @DeleteMapping("/{idCandidate}")
    public ResponseEntity<ResponseMessage> deleteCandidate(@PathVariable Long idCandidate) {
        candidateServiceImp.deleteCandidate(idCandidate);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(messageResponse("Candidate removed"));
    }
}
