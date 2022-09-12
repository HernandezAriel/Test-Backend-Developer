package com.testbackend.test.repositories;

import com.testbackend.test.models.entities.Candidate;
import com.testbackend.test.models.entities.CandidateByTechnology;
import com.testbackend.test.models.entities.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CandidateByTechnologyRepository extends JpaRepository<CandidateByTechnology, Long>{

    CandidateByTechnology findByCandidateAndTechnology(Candidate candidate, Technology technology);
    List<CandidateByTechnology> findByCandidate(Candidate candidate);
    List<CandidateByTechnology> findByTechnology(Technology technology);
}
