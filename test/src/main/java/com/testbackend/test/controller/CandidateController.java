package com.testbackend.test.controller;

import com.testbackend.test.exception.CandidateAlreadyExistsException;
import com.testbackend.test.exception.CandidateByTechnologyAlreadyExistsException;
import com.testbackend.test.exception.CandidateNotExistsException;
import com.testbackend.test.exception.TechnologyNotExistsException;
import com.testbackend.test.model.dto.CandidateDto;
import com.testbackend.test.model.entity.Candidate;
import com.testbackend.test.model.util.ResponseMessage;
import com.testbackend.test.repository.CandidateRepository;
import com.testbackend.test.service.Imp.CandidateService;
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
import java.util.Optional;

import static com.testbackend.test.util.UrlBuilder.buildURL;
import static com.testbackend.test.util.ResponseUtil.messageResponse;
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @Autowired
    CandidateRepository candidateRepository;

    @GetMapping
    public ResponseEntity<List<CandidateDto>> getAllCandidates(){
        return new ResponseEntity<>(candidateService.getAllCandidates(), HttpStatus.OK);
    }

    @GetMapping("/{idCandidate}")
    public ResponseEntity<CandidateDto> getCandidateById(@PathVariable Long idCandidate) throws CandidateNotExistsException{
        return ResponseEntity.ok(candidateService.getCandidateDtoById(idCandidate));
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> addCandidate(@Valid @RequestBody Candidate candidate) throws CandidateAlreadyExistsException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(buildURL("candidates", candidateService.addCandidate(candidate).getIdCandidate()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageResponse("Candidate has been created"));
    }

    @PutMapping("/{idCandidate}")
    public ResponseEntity<ResponseMessage> updateCandidate(@Valid @RequestBody Candidate candidate, @PathVariable Long idCandidate) throws CandidateNotExistsException {
        Optional<Candidate> candidateOptional = candidateRepository.findById(idCandidate);
        if (candidateOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            candidate.setIdCandidate(idCandidate);
            candidateRepository.save(candidate);
            return ResponseEntity.noContent().build();
        }
    }

        @PutMapping("/{idCandidate}/technologies/{idTechnology}")
        public ResponseEntity<ResponseMessage> addTechnologyToCandidate (@Valid @PathVariable Long idCandidate, @PathVariable Long idTechnology, @RequestParam Long experience) throws
        CandidateNotExistsException, TechnologyNotExistsException, CandidateByTechnologyAlreadyExistsException {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .location(buildURL("candidates", candidateService.addTechnologyToCandidate(idCandidate, idTechnology, experience).getDocumentNumber()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(messageResponse("Technology added to candidate"));
        }

        @DeleteMapping("/{idCandidate}")
        public ResponseEntity<ResponseMessage> deleteCandidate (@PathVariable Long idCandidate) throws
        CandidateNotExistsException {
            candidateService.deleteCandidate(idCandidate);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(messageResponse("Candidate removed"));
        }
    }